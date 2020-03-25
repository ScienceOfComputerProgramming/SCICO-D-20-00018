package com.gamaza.rest4cep.config.security.model;

import com.gamaza.rest4cep.design.model.Auditable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.gamaza.rest4cep.config.constant.EntityConstants.RELATION_FIELD_ROLES;
import static com.gamaza.rest4cep.config.constant.EntityConstants.TABLE_LDAP_ROLE;

@Entity
@Table(name = TABLE_LDAP_ROLE)
@Getter @Setter
public class Role extends Auditable implements GrantedAuthority {

    // Generated SerialVersionUID

    private static final long serialVersionUID = 5234559145186917776L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = RELATION_FIELD_ROLES)
    private List<User> users = new ArrayList<>();

    @Override
    public String getAuthority() {
        return name;
    }

}
