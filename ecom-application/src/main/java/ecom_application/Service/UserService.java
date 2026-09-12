package ecom_application.Service;

import ecom_application.Model.User;
import ecom_application.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
//    private List<User> userList = new ArrayList<>();
//    private AtomicLong idCounter = new AtomicLong(1);
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(User user) {
        // Generate a backend-controlled unique id
//        user.setId(idCounter.getAndIncrement());
        userRepository.save(user);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public boolean updateUser(Long id, User user) {
      return userRepository.findById(id).map(existingUser -> {
                    existingUser.setFirstName(user.getFirstName());
                    existingUser.setLastName(user.getLastName());
                    userRepository.save(existingUser);
                  return true;
              })
              .orElse(false);
    }

}

