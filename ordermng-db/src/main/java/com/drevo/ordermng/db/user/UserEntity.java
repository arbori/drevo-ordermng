package com.drevo.ordermng.db.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordermng_user")
public class UserEntity {
	@Id
	@Column(name = "user_name", length = 16)
    private String name;

	@Column(name = "user_fullname", length = 40)
    private String fullname;

	@Column(name = "user_email", length = 32)
	private String email;

	@Column(name = "user_emailConfirmed")
    private Boolean emailConfirmed;

	@Column(name = "user_askedConfimationSinse")
    private LocalDateTime askedConfimationSinse;

	@Column(name = "user_active")
	private Boolean active;
}
