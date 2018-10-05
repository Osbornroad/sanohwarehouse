package com.gmail.osbornroad.model.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gmail.osbornroad.util.DateTimeUtil;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import static javax.persistence.GenerationType.IDENTITY;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="users")
public class User extends BaseEntity {

//    private String userName;
    private String email;
    private String password;
//    private boolean enabled = true;
    private LocalDateTime registered;
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String name, String email, String password, /*boolean enabled, */LocalDateTime registered, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
//        this.enabled = enabled;
        this.registered = registered;
        this.roles = roles;
    }

    public User(Integer id, String name, String email, String password, /*boolean enabled, */LocalDateTime registered, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
//        this.enabled = enabled;
        this.registered = registered;
        this.roles = roles;
    }

   /* @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }*/

    @Column(name = "email")
    @Email
    @NotBlank
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    @NotBlank
    @Length(min = 6)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*@Column(name = "enabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }*/

    @Column(name = "registered")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registered=" + registered +
                '}';
    }
}
