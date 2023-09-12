package com.drevo.ordermng.user;

public class UserMapper {
    private UserMapper() {
    }

    /**
     * Create a new user entity object based in the state of the dto parameter.
     * @param user User dto to be mapped.
     * @return A new user entity
     */
    public static UserEntity dtoToEntity(UserDTO user) {
        return new UserEntity(
            user.getName(), 
            user.getFullName(), 
            user.getEmail(), 
            user.isEmailConfirmed(),
            user.getAskedConfimationSinse(),
            user.isActive());        
    }

    /**
     * Create a new user dto object based in the state of the entity parameter.
     * @param entity User entity to be mapped.
     * @return A new user dto
     */
    public static UserDTO entityToDto(UserEntity entity) {
        return new UserDTO(
            entity.getName(), 
            entity.getFullName(), 
            entity.getEmail(), 
            entity.getEmailConfirmed(),
            entity.getAskedConfimationSinse(),
            entity.getActive()
        );
    }
}
