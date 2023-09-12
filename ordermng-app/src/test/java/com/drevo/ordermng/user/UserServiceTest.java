package com.drevo.ordermng.user;

import static org.junit.Assert.assertTrue;
import java.util.Optional;

import org.junit.Test;
import com.drevo.ordermng.exception.UserExistsException;

public class UserServiceTest {
    private final UserService userService;
    private final UserDTO user;

    public UserServiceTest() {
        user = new UserDTO("asmith", "Alexender Smith", "asmith@company.com");

        userService = new UserService(new UserRepositoryImpl());
    }

    @Test
    public void userCreate() throws UserExistsException {
        UserDTO userRegister = userService.registerNewUser(user);

        assertTrue("The user was not register properly.", 
            (user != userRegister) && user.equals(userRegister));
    }

    @Test
    public void userRead() {
        Optional<UserDTO> userRetrived = userService.retrieve(user.getName());

        assertTrue("User does not found", userRetrived.isPresent());

        UserDTO userRegister = userRetrived.get();

        assertTrue("The user was not register properly.",
            (user != userRegister) && user.equals(userRegister));
    }

    @Test
    public void userUpdate() {
        String newFullname = "Alexender Smith Walker";
        String newEmail = "aswalker@company.com";

        Optional<UserDTO> userRetrived = userService.changeUserInformation(user);

        assertTrue("User does not found", userRetrived.isPresent());

        UserDTO userRegister = userRetrived.get();

        boolean equals = (user != userRegister) || 
            (userRegister.getName().equals(user.getName()) 
                && userRegister.getFullName().equals(newFullname)
                && userRegister.getEmail().equals(newEmail)
                && userRegister.getAskedConfimationSinse().equals(user.getAskedConfimationSinse())
                && userRegister.isActive().equals(user.isActive())
                && userRegister.isEmailConfirmed().equals(user.isEmailConfirmed()));

        assertTrue("The user was not register properly.", equals);
    }

    @Test
    public void userDelete() {
        Optional<UserDTO> userRetrived = userService.changeUserInformation(user);

        assertTrue("User does not found", userRetrived.isPresent());

        userService.remove(user.getName());
        userRetrived = userService.changeUserInformation(user);

        assertTrue("User still exists", !userRetrived.isPresent());
    }
}
