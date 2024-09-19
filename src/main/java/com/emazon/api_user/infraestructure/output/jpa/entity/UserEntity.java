package com.emazon.api_user.infraestructure.output.jpa.entity;

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
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "document_number")
    private Integer documentNumber;
    @Column(name = "cell_phone")
    private String cellPhone;
    private LocalDate birthdate;
    private String email;
    private String password;
    @Column(name = "id_rol")
    private Integer idRol;
}
