package ru.fijirash.pp.spring_security.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role")
    private String role;

    public Role() {
    }

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role(int id) {
        this.id = id;
    }

    public Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role.substring(5);
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
