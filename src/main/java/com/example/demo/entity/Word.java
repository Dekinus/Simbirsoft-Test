package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity

@Table(name = "WORD", schema = "MY_BAZA")

public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "word", nullable = false)
    private String word;

    @Column(name = "counter", nullable = false)
    private Integer counter;

}
