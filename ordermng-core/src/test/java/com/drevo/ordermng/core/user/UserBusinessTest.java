package com.drevo.ordermng.core.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Test;

import com.drevo.ordermng.core.EmailConfirmationException;
import com.drevo.ordermng.core.UserExistsException;

public class UserBusinessTest {
    private UserBusiness userBusiness = new UserBusinessImpl();

    public UserBusinessTest() {
        try {
            userBusiness.registerNewUser(new UserDTO("johnsnow", "John Snow", "johnsnow@google.com"));
            userBusiness.registerNewUser(new UserDTO("richardf", "Richard Fleury", "fleury@goole.com"));
        } catch (UserExistsException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isValidTest() {
        assertTrue("A all null attributes user is not valid",
            !userBusiness.isValid(new UserDTO(null, null, null)));

        // Test user name
        assertTrue("A user with null name is invalid",
            !userBusiness.isValid(new UserDTO(null, "xxxxxxxxxxxxxxxxxxxx", "bla@server.com")));
        assertTrue("A user with empty name is invalid",
            !userBusiness.isValid(new UserDTO("", "xxxxxxxxxxxxxxxxxxxx", "bla@server.com")));
        assertTrue("A user with name's size less than 8 is invalid",
            !userBusiness.isValid(new UserDTO("xxx", "xxxxxxxxxxxxxxxxxxxx", "bla@server.com")));
        assertTrue("A user with name's size great than 16 is invalid",
            !userBusiness.isValid(new UserDTO("xxxxxxxxxxxxxxxxx", "xxxxxxxxxxxxxxxxxxxx", "bla@server.com")));

        // Test full name
        assertTrue("A user with null full name is invalid",
            !userBusiness.isValid(new UserDTO("xxxxxxxx", null, "bla@server.com")));
        assertTrue("A user with empty full name is invalid",
            !userBusiness.isValid(new UserDTO("xxxxxxxx", "", "bla@server.com")));
        assertTrue("A user with full name's size less than 10 is invalid",
            !userBusiness.isValid(new UserDTO("xxxxxxxx", "xxxxxxxxx", "bla@server.com")));
        assertTrue("A user with full name's size great than 40 is invalid",
            !userBusiness.isValid(new UserDTO("xxxxxxxx", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", "bla@server.com")));

        // Test email
        assertTrue("A user with null email is invalid",
            !userBusiness.isValid(new UserDTO("xxxxxxxx", "xxxxxxxxxxxxxxxxxxxx", null)));
        assertTrue("A user with empty full name is invalid",
            !userBusiness.isValid(new UserDTO("xxxxxxxx", "xxxxxxxxxxxxxxxxxxxx", "")));
    }

    @Test
    public void changeUserInformationWithoutStoredUserTest() {
        UserDTO src = new UserDTO("AbraKadabra", "Abra Kadabra Magic", "kadabra@magic.com");
        
        Optional<UserDTO> tarOpt = userBusiness.changeUserInformation(src);

        assertTrue(String.format("A user with name %s does not exist, but a user was retrieved.", src.getName()), !tarOpt.isPresent());
    }

    @Test
    public void changeUserInformationTest() {
        UserDTO src = new UserDTO("johnsnow", "John Snow McFly", "test@test.com");
        
        Optional<UserDTO> tarOpt = userBusiness.changeUserInformation(src);

        assertTrue(String.format("There is no user with name %s", src.getName()), tarOpt.isPresent());

        UserDTO tar = tarOpt.get();

        assertEquals("Source and taget users are not same user, since have different names.", src.getName(), tar.getName());
        
        assertEquals("Source and taget users with different full names.", src.getFullName(), tar.getFullName());
        assertEquals("Source and taget users with different email.", src.getEmail(), tar.getEmail());
    }

    @Test
    public void confirmUserEmailForUnexistedUser() throws EmailConfirmationException {
        String name = "AbraKadabra";
        String email = "kadabra@magic.com";

        Optional<UserDTO> userOptional = userBusiness.confirmUserEmail(name, email);

        assertTrue("An inexistent user has been your email confirmed.", !userOptional.isPresent());
    }

    @Test
    public void confirmUserEmailTest() throws EmailConfirmationException {
        String name = "johnsnow";
        String email = "johnsnow@google.com";

        Optional<UserDTO> userOptional = userBusiness.retrieve(name);

        assertTrue("User does not exists.", userOptional.isPresent());

        userBusiness.askEmailConfirmation(userOptional.get());
        
        userOptional = userBusiness.confirmUserEmail(name, email);

        assertTrue(String.format("The user %s has not been retrieved.", name), userOptional.isPresent());
        assertEquals("The users' emails are different.", email, userOptional.get().getEmail());
        assertTrue("The user has not been your email confirmed.", userOptional.get().getEmailConfirmed());
    }
}
