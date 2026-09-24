package ecom_application.Service;


import ecom_application.Model.CartItem;
import ecom_application.Model.Product;
import ecom_application.Model.User;
import ecom_application.Repository.CartItemRepository;
import ecom_application.Repository.ProductRepository;
import ecom_application.Repository.UserRepository;
import ecom_application.dto.CartItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    public boolean addToCart(String userId, CartItemRequest request) {
        // Implement the logic to add the item to the cart
        // This may involve checking if the product exists, calculating total price, etc.
        Optional<Product> productOpt = productRepository.findById(request.getProductId());
        if(productOpt.isEmpty()) return false;

        Product product=productOpt.get();
        if(product.getStockQuantity()<request.getQuantity()) return false; // Not enough stock

        Optional<User> userOpt=userRepository.findById(Long.valueOf(userId));
        if(userOpt.isEmpty()) return false;

        User user=userOpt.get();

        CartItem existingCartItem=cartItemRepository.findByUserAndProduct(user, product);

    if(existingCartItem!=null){
        //Update the Quantity and total price
        existingCartItem.setQuantity(existingCartItem.getQuantity()+ request.getQuantity());
        existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
        // persist the updated cart item
        cartItemRepository.save(existingCartItem);
    }
    else{
       // Create a new CartItem
     CartItem cartItem=new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(request.getQuantity());
        cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
        cartItemRepository.save(cartItem);
    }

        return true;
    }

    public Boolean removeFromCart(String userID, CartItemRequest request){
        // Find product and user
        Optional<Product> productOpt = productRepository.findById(request.getProductId());
        if (productOpt.isEmpty()) return false;
        Product product = productOpt.get();

        Optional<User> userOpt = userRepository.findById(Long.valueOf(userID));
        if (userOpt.isEmpty()) return false;
        User user = userOpt.get();

        // Find cart item by user and product
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);
        if (cartItem == null) return false;

        // Determine how many to remove; null or zero means remove entire item
        Integer removeQty = request.getQuantity();
        if (removeQty == null || removeQty <= 0 || removeQty >= cartItem.getQuantity()) {
            // remove the cart item completely
            cartItemRepository.delete(cartItem);
        } else {
            // reduce quantity and update price
            cartItem.setQuantity(cartItem.getQuantity() - removeQty);
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            cartItemRepository.save(cartItem);
        }

        return true;
    }

}
