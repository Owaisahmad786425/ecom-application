package ecom_application.Service;

import ecom_application.Enums.OrderStatus;
import ecom_application.Model.CartItem;
import ecom_application.Model.Order;
import ecom_application.Model.OrderItem;
import ecom_application.Model.User;
import ecom_application.Repository.OrderRepository;
import ecom_application.Repository.UserRepository;
import ecom_application.dto.OrderItemDTO;
import ecom_application.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final CartService cartService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    public Optional<OrderResponse> createOrder(String userId) {
        ;
        //validate for cart items and userId
        List<CartItem> cartItems=cartService.getCartItems(userId);
        if(cartItems.isEmpty()) {
            return Optional.empty();
        }

        Optional<User> userOptional=userRepository.findById(Long.valueOf(userId));
        if(userOptional.isEmpty()) {
            return Optional.empty();
        }
        User user=userOptional.get();
        //calculate total price
        BigDecimal total=BigDecimal.ZERO;
        for(int i=0;i<cartItems.size();i++){
            total=total.add(cartItems.get(i).getPrice());
        }

        //create order and save to database
        Order order=new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalPrice(total);

        List<OrderItem> orderItems=cartItems.stream()
                .map(item-> new OrderItem(
                        null,
                        item.getProduct(),
                        item.getQuantity(),
                        item.getPrice(),
                        order
                ))
                .toList();

        order.setItems(orderItems);
         Order savedOrder=orderRepository.save(order);
        //Clear the Cart
        cartService.clearCart(userId);
        return Optional.of(mapToOrderResponse(savedOrder));
    }

    public OrderResponse mapToOrderResponse(Order savedOrder){
        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getTotalPrice(),
                savedOrder.getStatus(),
                savedOrder.getItems().stream()
                        .map(item->new OrderItemDTO(
                                item.getId(),
                                item.getProduct().getId(),
                                item.getQuantity(),
                                item.getPrice().multiply(new BigDecimal(item.getQuantity())),
                                null
                        ))
                        .toList(),
                savedOrder.getCreatedAt()
        );
    }
}
