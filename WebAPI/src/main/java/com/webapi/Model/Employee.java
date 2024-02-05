package com.webapi.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "Hiba a 'firstName' mezőben. A mező nem lehet üres.")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @NotEmpty(message = "Hiba a 'lastName' mezőben. A mező nem lehet üres.")
    private String lastName;
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Hiba az 'email' mezőben. A mező nem lehet üres.")
    @Email(message = "Hiba az 'email' mezőben. Rossz email formátum.")
    private String email;
}