package com.kola.management.event.user.model;

import com.kola.management.event.kernel.model.BaseKernelModel;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseKernelModel implements UserDetails {

    public static final String userIdProp = "userId";
    public static final String userNameProp = "username";
    public static final String firstNameProp = "firstname";
    public static final String lastNameProp = "lastname";
    public static final String emailProp = "email";
    public static final String passwordProp = "password";
    public static final String confirmPasswordProp = "confirmpassword";
    public static final String creationDateProp = "creationDate";
    

    private Long userId;


    private String username;

    private String lastname;

    private String firstname;

    private String email;

    private String password;

    @Transient
    private String confirmpassword;

    @Transient
    private List<Role> rolesObject;

    private List<String> roles;

    private LocalDateTime creationDate;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

    public void addRole(Role role) {
        role.setRoleId(role.getRoleId());
        this.rolesObject.add(role);
    }
    public void addRole(String role){
        roles.add(role);
    }

    public void removeRole(String role){
        roles.remove(role);
    }

    public void removeRole(Role role) {
        for (int i = 0; i < this.rolesObject.size(); i++) {
            Role r = rolesObject.get(i);
            if (Objects.equals(r.getRoleId(), role.getRoleId())) {
                roles.remove(r);
                break;
            }
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<String> roles = getRoles();
        if (roles != null){
            roles.forEach(r -> grantedAuthorities.add(new SimpleGrantedAuthority(r)));
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
