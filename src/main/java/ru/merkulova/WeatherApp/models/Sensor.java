package ru.merkulova.WeatherApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.List;

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

    @Column(name="name", unique = true)
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    @NotEmpty(message ="Name should not be empty")
    private String name;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sensor")
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private List<Measurement> measurements;
}

