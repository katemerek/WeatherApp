package com.github.katemerek.WeatherApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="Sensor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sensor implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name", unique = true, insertable = false, updatable = false)
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    @NotBlank(message ="Name should not be empty")
    private String name;
}

