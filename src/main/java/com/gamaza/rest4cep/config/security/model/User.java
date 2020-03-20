package com.gamaza.rest4cep.config.security.model;

import com.gamaza.rest4cep.mysql.model.Auditable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.gamaza.rest4cep.config.constant.EntityConstants.*;

@Entity
@Table(name = TABLE_LDAP_USER)
@Getter @Setter
public class User extends Auditable implements UserDetails {

    // Generated SerialVersionUID
    private static final long serialVersionUID = 4484218652392603649L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = TABLE_LDAP_USER_ROLE,
            joinColumns = @JoinColumn(name = JOIN_COLUMN_USER_ID),
            inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_ROLE_ID)
    )
    private List<Role> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
