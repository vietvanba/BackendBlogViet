package com.blog.authservice.entities;

import com.blog.authservice.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Account {
    @Id
    private String username;
    private String password;
    private Date createdTime;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String avatar;
    @OneToMany(mappedBy = "account")
    private List<PersonalAddress> personalAddress;
    @Email(message = "Please correct your email")
    private String email;
    private String phoneNumber;
    private Boolean active;
    private String noteActive;
    private Role role;
}
