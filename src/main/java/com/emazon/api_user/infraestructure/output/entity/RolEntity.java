package com.emazon.api_user.infraestructure.output.entity;

import com.emazon.api_user.infraestructure.util.ConstantsOutput;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "rol")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = ConstantsOutput.ROL, fetch = FetchType.LAZY)
    private List<UserEntity> users;
}
