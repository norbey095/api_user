package com.emazon.api_user.infraestructure.output.entity;

import com.emazon.api_user.infraestructure.util.ConstantsInfraestructure;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = ConstantsInfraestructure.LAST_NAME)
    private String lastName;
    @Column(name = ConstantsInfraestructure.DOCUMENT_NUMBER)
    private Integer documentNumber;
    @Column(name = ConstantsInfraestructure.CELL_PHONE)
    private String cellPhone;
    private LocalDate birthdate;
    private String email;
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = ConstantsInfraestructure.ID_ROL)
    private RolEntity rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(ConstantsInfraestructure.ROLE + rol.getName()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
