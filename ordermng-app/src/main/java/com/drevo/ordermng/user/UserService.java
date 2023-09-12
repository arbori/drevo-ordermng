package com.drevo.ordermng.user;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends UserBusiness {
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void askEmailConfirmation(UserDTO user) {
        Optional<UserEntity> userEntityOptional = userRepository.findActiveByName(user.getName());

        if(userEntityOptional.isPresent()) {
            UserEntity entity = userEntityOptional.get();
            entity.setAskedConfimationSinse(LocalDateTime.now());

            userRepository.save(entity);
        }
    }

    @Override
    public UserDTO save(UserDTO user) {
        if(user == null || !this.isValid(user)) {
            throw new IllegalArgumentException("User is null or invalid");
        }
    
        return UserMapper.entityToDto(
            userRepository.save(
                UserMapper.dtoToEntity(user)));
    }

    @Override
    public Optional<UserDTO> retrieve(String name) {
        Optional<UserEntity> userEntityOptional = userRepository.findActiveByName(name);

        if(userEntityOptional.isPresent()) {
            return Optional.of(UserMapper.entityToDto(userEntityOptional.get()));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDTO> remove(String name) {
        Optional<UserEntity> userEntityOptional = userRepository.findActiveByName(name);

        if(userEntityOptional.isPresent()) {
            UserEntity entity = userEntityOptional.get();
            entity.setActive(false);

            entity = userRepository.save(entity);

            return Optional.of(UserMapper.entityToDto(entity));
        }
        else {
            return Optional.empty();
        }
    }
}
