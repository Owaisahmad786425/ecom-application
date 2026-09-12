package ecom_application.Service;

import ecom_application.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong(1);

    public List<User> getAllUsers() {
        return userList;
    }

    public List<User> createUser(User user) {
        // Generate a backend-controlled unique id
        user.setId(idCounter.getAndIncrement());
        userList.add(user);
        return userList;
    }

    public User getUser(Long id) {
        if (id == null) return null;
        for (User user : userList) {
            if (user.getId() == id.longValue()) {
                return user;
            }
        }
        return null;
    }

    public boolean updateUser(Long id, User user) {
      return userList.stream()
              .filter(user1->user1.getId()==id.longValue())
                .findFirst()
              .map(existingUser -> {
                    existingUser.setFirstName(user.getFirstName());
                    existingUser.setLastName(user.getLastName());
                  return true;
              })
              .orElse(false);
    }

}

