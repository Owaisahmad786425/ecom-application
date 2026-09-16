package ecom_application.Service;

import ecom_application.Model.Address;
import ecom_application.Model.User;
import ecom_application.Repository.UserRepository;
import ecom_application.dto.AddressDTO;
import ecom_application.dto.UserRequest;
import ecom_application.dto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //    private List<User> userList = new ArrayList<>();
//    private AtomicLong idCounter = new AtomicLong(1);
    public List<UserResponse> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(this::maptoUserResponse)
                .collect(Collectors.toList());
    }

    public void createUser(UserRequest userRequest) {
        // Generate a backend-controlled unique id
//        user.setId(idCounter.getAndIncrement());
        User user = new User();
        updateUserFromRequest(user, userRequest);
        userRepository.save(user);
    }

    public Optional<UserResponse> getUser(Long id) {
        return userRepository.findById(id)
                .map(this::maptoUserResponse);
    }

    public boolean updateUser(Long id, UserRequest updatedUserRequest) {
        return userRepository.findById(id).map(existingUser -> {
                    updateUserFromRequest(existingUser, updatedUserRequest);
                    userRepository.save(existingUser);
                    return true;
                })
                .orElse(false);
    }

    private UserResponse maptoUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRole());
         if(user.getAddress()!=null){
             AddressDTO addressDTO = new AddressDTO();
             addressDTO.setStreet(user.getAddress().getStreet());
             addressDTO.setCity(user.getAddress().getCity());
             addressDTO.setState(user.getAddress().getState());
             addressDTO.setZipCode(user.getAddress().getZipCode());
             userResponse.setAddress(addressDTO);
         }

        return userResponse;
    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());

        if (userRequest.getAddress() != null) {
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setZipCode(userRequest.getAddress().getZipCode());
            address.setCountry(userRequest.getAddress().getCountry());
            user.setAddress(address);
        }
    }
}

