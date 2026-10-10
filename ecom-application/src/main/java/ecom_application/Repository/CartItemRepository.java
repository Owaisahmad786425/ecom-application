package ecom_application.Repository;


import ecom_application.Model.CartItem;
import ecom_application.Model.Product;
import ecom_application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
CartItem findByUserAndProduct(User user, Product product);

List<CartItem> findByUser(User user);

@Transactional
void deleteByUserAndProduct(User user, Product product);

void deleteByUser(User user);
}
