package ecom_application.Repository;


import ecom_application.Model.CartItem;
import ecom_application.Model.Product;
import ecom_application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
CartItem findByUserAndProduct(User user, Product product);
}
