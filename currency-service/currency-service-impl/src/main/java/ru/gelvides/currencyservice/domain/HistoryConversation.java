package ru.gelvides.currencyservice.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Accessors(chain = true)
@Entity
@Table(name = "history_conversation")
public class HistoryConversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "base", nullable = false)
    private Currency baseCurrency;

    @OneToOne(optional = false)
    @JoinColumn(name = "target", nullable = false)
    private Currency targetCurrency;

    @Column(name = "course")
    private double course;

    @Column(name = "date_conversation")
    private LocalDate dateConversation;
}