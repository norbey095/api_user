package com.emazon.api_user.domain.model;

import java.time.LocalDate;

public class UserSave {
    private String name;
    private String lastName;
    private String documentNumber;
    private String cellPhone;
    private LocalDate birthdate;
    private String email;
    private String password;
    private Integer idRol;

    private UserSave(UserBuilder builder) {
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.documentNumber = builder.documentNumber;
        this.cellPhone = builder.cellPhone;
        this.birthdate = builder.birthdate;
        this.email = builder.email;
        this.password = builder.password;
        this.idRol = builder.idRol;
    }

    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public String getDocumentNumber() { return documentNumber; }
    public String getCellPhone() { return cellPhone; }
    public LocalDate getBirthdate() { return birthdate; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Integer getIdRol() { return idRol; }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String name;
        private String lastName;
        private String documentNumber;
        private String cellPhone;
        private LocalDate birthdate;
        private String email;
        private String password;
        private Integer idRol;

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setDocumentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }

        public UserBuilder setCellPhone(String cellPhone) {
            this.cellPhone = cellPhone;
            return this;
        }

        public UserBuilder setBirthdate(LocalDate birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setIdRol(Integer idRol) {
            this.idRol = idRol;
            return this;
        }

        public UserSave build() {
            return new UserSave(this);
        }
    }
}
