package com.drevo.ordermng.user;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.regex.Pattern;

import com.drevo.ordermng.exception.EmailConfirmationException;
import com.drevo.ordermng.exception.UserExistsException;

public abstract class UserBusiness {
    private static final int NAME_MIN_SIZE = 8;
    private static final int NAME_MAX_SIZE = 16;

    private static final int FULLNAME_MIN_SIZE = 10;
    private static final int FULLNAME_MAX_SIZE = 40;

    private static final long EMAIL_CONFIRMATION_TIMEOUT = 5;

    private static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    
    private final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

    /**
     * Check if user's informations is valid.
     * @param user
     * @return Return true if the user object is valid
     */
    public boolean isValid(UserDTO user) {
        boolean valid = 
            user.getName() != null && !user.getName().isEmpty() && user.getName().length() >= NAME_MIN_SIZE  && user.getName().length() <= NAME_MAX_SIZE &&
            user.getFullName() != null && !user.getFullName().isEmpty() && user.getFullName().length() >= FULLNAME_MIN_SIZE  && user.getFullName().length() <= FULLNAME_MAX_SIZE &&
            user.getEmail() != null && !user.getEmail().isEmpty() &&
            user.isEmailConfirmed() != null &&
            user.isActive() != null;
    
         
        return valid && emailPattern.matcher(user.getEmail()).matches();
    }

    /**
     * Try to register a new user, saved it only if it is a new user.
     * @param user The new user will be tryed to register.
     * @throws UserExistsException Throw UserExistsException if the user already exists with the same name.
     */
    public UserDTO registerNewUser(UserDTO user) throws UserExistsException {
        Optional<UserDTO> retrieved = this.retrieve(user.getName());

        if(retrieved.isPresent()) {
            throw new UserExistsException(String.format("A user with name %s already exists.", user.getName()));
        }

        return this.save(user);
    }

    /**
     * Find a user with the same user name of src and change it.
     * @param src The new version of the user.
     * @return Return the changed user or Optional empty if the user do not exist.
     */
    public Optional<UserDTO> changeUserInformation(UserDTO src) {
        if(!isValid(src)) {
            return Optional.empty();
        }

        Optional<UserDTO> tar = retrieve(src.getName());

        if(tar.isPresent() && tar.get().getName().equals(src.getName())) {
            tar.get().setEmail(src.getEmail());
            tar.get().setFullName(src.getFullName());

            return tar;
        }

        return Optional.empty();
    }

    /**
     * Send an email to confirm the user is real.
     * @param user The user needed to confirm.
     */
    public abstract void askEmailConfirmation(UserDTO user);

    /**
     * Flag that user has been confirmed the email.
     * @param name User name that confirmed email.
     * @param email User's email confirmed by user.
     * @return Return the Optional<User> for user that confirm email or an empty optional if user does not exist.
     */
    public Optional<UserDTO> confirmUserEmail(String name, String email) throws EmailConfirmationException {
        Optional<UserDTO> found = this.retrieve(name);

        if(!found.isPresent() || !found.get().getEmail().equals(email)) {
            return Optional.empty();
        }

        if(found.get().getAskedConfimationSinse() == null) {
            throw new EmailConfirmationException("The user have no request to confirm email.");
        }

        long daysBetween = ChronoUnit.DAYS.between(found.get().getAskedConfimationSinse(), LocalDateTime.now());

        if(daysBetween > EMAIL_CONFIRMATION_TIMEOUT) {
            throw new EmailConfirmationException(String.format("Email confirmation must be done in maximum %d days, but %d passed.", EMAIL_CONFIRMATION_TIMEOUT, daysBetween));
        }

        found.get().setEmailConfirmed(true);
        found.get().setAskedConfimationSinse(null);

        return found;
    }

    public abstract UserDTO save(UserDTO user);

    public abstract Optional<UserDTO> retrieve(String name);

    public abstract Optional<UserDTO> remove(String name);
}
