package com.ordermng.core.user;

import com.ordermng.core.dto.UserDTO;

public interface UserBusiness {
    static final int NAME_MIN_SIZE = 8;
    static final int NAME_MAX_SIZE = 16;

    static final int FULLNAME_MIN_SIZE = 20;
    static final int FULLNAME_MAX_SIZE = 40;

    /**
     * Check if user's informations is valid.
     * @param user
     * @return Return true if the user object is valid
     */
    public default boolean isValid(UserDTO user) {
        return 
            user.getName() != null && !user.getName().isEmpty() && user.getName().length() >= NAME_MIN_SIZE  && user.getName().length() <= NAME_MAX_SIZE &&
            user.getFullName() != null && !user.getFullName().isEmpty() && user.getFullName().length() >= FULLNAME_MIN_SIZE  && user.getFullName().length() <= FULLNAME_MAX_SIZE &&
            user.getEmail() != null && !user.getEmail().isEmpty() && user.getEmail().length() > 0 &&
            user.getEmailConfirmed() != null &&
            user.getActive() != null;
    }

    public void registerNewUSer(UserDTO user);

    public UserDTO findUser(String name);

    public void changeUserInformation(UserDTO user);

    public void removeUser(String name);

    public void askEmailConfirmation(UserDTO user);

    public void confirmUserEmail(String name, String email);
}
