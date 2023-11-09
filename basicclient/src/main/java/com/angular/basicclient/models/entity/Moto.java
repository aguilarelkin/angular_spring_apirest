package com.angular.basicclient.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "motos")
public class Moto implements Serializable {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "No puede estar vacío")
    @Size(min = 3, max = 50)
    @Column(unique = true, nullable = false)
    private String name;

    @NotEmpty(message = "No puede estar vacío")
    @Column(nullable = false)
    private String image;

    @NotEmpty(message = "No puede estar vacío")
    @Column(nullable = false)
    private String description;
}
