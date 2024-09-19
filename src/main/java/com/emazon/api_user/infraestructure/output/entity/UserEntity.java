package com.emazon.api_user.infraestructure.output.entity;

import com.emazon.api_user.infraestructure.output.util.Constants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = Constants.LAST_NAME)
    private String lastName;
    @Column(name = Constants.DOCUMENT_NUMBER)
    private Integer documentNumber;
    @Column(name = Constants.CELL_PHONE)
    private String cellPhone;
    private LocalDate birthdate;
    private String email;
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Constants.ID_ROL)
    private RolEntity rol;
}
