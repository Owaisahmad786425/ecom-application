package ecom_application.Controller;


import ecom_application.Service.CartService;
import ecom_application.dto.CartItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId, @RequestBody CartItemRequest request) {
       if(!cartService.addToCart(userId,request)){
           return ResponseEntity.badRequest().body("Product out of stock or User not found or Product not found");
       }

       return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestHeader("X-User-ID") String userId, @RequestBody CartItemRequest request){
          if(!cartService.removeFromCart(userId,request)){
              return ResponseEntity.badRequest().body("Product not found in cart or User not found or Product not found");
          }
          return ResponseEntity.status(HttpStatus.OK).build();
    }

}
