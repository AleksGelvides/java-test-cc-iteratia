package ru.gelvides.currencyservice.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "numcode")
    private String numcode;

    @Column(name = "charcode")
    private String charcode;

    @Column(name = "nominal")
    private int nominal;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private double value;

    @Column(name = "date")
    private LocalDate date;
}