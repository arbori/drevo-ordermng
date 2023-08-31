package com.drevo.ordermng.core.user;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserBusinessImpl extends UserBusiness {
    Map<String, UserDTO> userMap  = new HashMap<String, UserDTO>();

    @Override
    public void askEmailConfirmation(UserDTO user) {
        user.setAskedConfimationSinse(LocalDateTime.now());
    }

    @Override
    public UserDTO save(UserDTO user) {
        UserDTO persist = this.userMap.get(user.getName());

        if(persist != null) {
            persist.setEmail(user.getEmail());
            persist.setFullName(user.getFullName());
            persist.setEmailConfirmed(user.getEmailConfirmed());
            persist.setActive(user.getActive());
        }
        else {
            persist = new UserDTO(user);

            this.userMap.put(user.getName(), persist);
        }

        return persist;
    }

    @Override
    public Optional<UserDTO> retrieve(String name) {
        UserDTO user = userMap.get(name);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    @Override
    public Optional<UserDTO> remove(String name) {
        if(userMap.containsKey(name))
            return Optional.of(userMap.remove(name));
        else
            return Optional.empty();
    }
}
