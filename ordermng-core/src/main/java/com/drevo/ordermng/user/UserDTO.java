package com.drevo.ordermng.user;

import java.time.LocalDateTime;

public class UserDTO {
    private String name;
    private String fullName;
    private String email;
    private Boolean emailConfirmed;
    private LocalDateTime askedConfimationSinse;
    private Boolean active;
    
    public UserDTO(String name, String fullName, String email) {
        this.name = name;
        this.fullName = fullName;
        this.email = email;
        this.emailConfirmed = false;
        this.askedConfimationSinse = null;
        this.active = true;
    }

    UserDTO(String name, String fullName, String email, Boolean emailConfirmed, LocalDateTime askedConfimationSinse, Boolean active) {
        this.name = name;
        this.fullName = fullName;
        this.email = email;
        this.emailConfirmed = emailConfirmed;
        this.askedConfimationSinse = askedConfimationSinse;
        this.active = active;
    }

    public UserDTO(UserDTO user) {
        this.name = user.name;
        this.fullName = user.fullName;
        this.email = user.email;
        this.emailConfirmed = user.emailConfirmed;
        this.askedConfimationSinse = user.askedConfimationSinse;
        this.active = user.active;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isEmailConfirmed() {
        return emailConfirmed;
    }
    void setEmailConfirmed(Boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public LocalDateTime getAskedConfimationSinse() {
        return askedConfimationSinse;
    }
    void setAskedConfimationSinse(LocalDateTime askedConfimationSinse) {
        this.askedConfimationSinse = askedConfimationSinse;
    }

    public Boolean isActive() {
        return active;
    }
    void setActive(Boolean active) {
        this.active = active;
    }
}
