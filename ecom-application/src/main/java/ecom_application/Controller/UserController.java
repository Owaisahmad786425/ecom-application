package ecom_application.Controller;

import ecom_application.Model.User;
import ecom_application.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/user")
    public ResponseEntity<List<User>> getAllusers(){

        return new ResponseEntity<>(userService.getAllUsers(),
                HttpStatus.OK);
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user=userService.getUser(id);
        if(user==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user,
                HttpStatus.OK);
    }

    @PostMapping("/api/user")
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.ok("User created successfully");
    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser){
         boolean updated= userService.updateUser(id,updatedUser);
         if(updated){
             return new ResponseEntity<>("User updated successfully",HttpStatus.OK);
         }
         return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

}
