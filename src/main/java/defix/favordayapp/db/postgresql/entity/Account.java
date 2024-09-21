package defix.favordayapp.db.postgresql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 30)
    @Column(name = "username", length = 30) //ch
    private String username;

    @Size(max = 15)
    @Column(name = "name", length = 15)
    private String name;

    @Size(max = 15)
    @Column(name = "surname", length = 15)
    private String surname;

    @Size(max = 50)
    @Column(name = "password", length = 255)
    private String password;

    @ColumnDefault("'1978-01-01'")
    @Column(name = "register_date")
    private LocalDate date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

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

    @Override
    public boolean isEnabled() {
        return true;
    }
}