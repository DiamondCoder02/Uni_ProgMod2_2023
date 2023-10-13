package com.lesson6;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class User {
    private Long id;
    @NotBlank(message = "Hibás név: a 'name' mező nem lehet üres.")
    private String name;
    @Email(message = "Hibás email: az 'email' mezőben megadott adat formátuma nem megfelelő.")
    @NotNull(message = "Hibás email: az 'email' mező kitöltése kötelező.")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}