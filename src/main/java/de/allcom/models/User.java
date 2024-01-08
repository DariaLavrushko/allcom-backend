package de.allcom.models;

import de.allcom.models.token.Token;<<<<<<<HEAD
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;=======
import jakarta.persistence.*;

import java.util.Collection;>>>>>>>25f d9f5(add jwt,modify entity User,create entity Role,Address,Token.Modify liquidbase files)
import java.util.List;
import java.util.Objects;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;<<<<<<<HEAD
import org.springframework.security.core.userdetails.UserDetails;

@Setter @Getter=======
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data >>>>>>>25f d9f5(add jwt,modify entity User,create entity Role,Address,Token.Modify liquidbase files)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = { "tokens", "hashPassword" })
@Table(name = "account")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String hashPassword;

    <<<<<<<HEAD @Column(nullable=false,length=13)
    private String phoneNumber;

    @Column
    private String companyName;

    @Column(length = 13)
    private String taxNumber;=======
    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(length = 255)
    private String companyName;

    @Column(length = 13)
    private String inn;>>>>>>>25fd9f5 (add jwt, modify entity User, create entity Role,Address, Token. Modify liquidbase files)

    @Column(length = 100)
    private String position;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isChecked;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isBlocked;

    @Column(nullable = false)
    private LocalDateTime updateAt;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Token> tokens;

    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer()
                .getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer()
                        .getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass)
            return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role.getAuthorities());
    }

    @Override
    public String getPassword() {
        return hashPassword;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isChecked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !isBlocked;
    }
}
