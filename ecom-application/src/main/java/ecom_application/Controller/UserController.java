package ecom_application.Controller;

import ecom_application.Model.User;
import ecom_application.Service.UserService;
import ecom_application.dto.UserRequest;
import ecom_application.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping
//    @RequestMapping(value="/api/user", method = RequestMethod.GET)
    public ResponseEntity<List<UserResponse>> getAllusers(){

        return new ResponseEntity<>(userService.getAllUsers(),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        Optional<UserResponse> user=userService.getUser(id);
        if(user.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user.get(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
        return ResponseEntity.ok("User created successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequest updatedUserRequest){
        boolean updated = userService.updateUser(id, updatedUserRequest);
        if(updated){
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        }
         return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

}
