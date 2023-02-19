package com.example.cocomarket.Entity;

import java.time.LocalDate;
import java.util.Set;

import lombok.*;

import javax.persistence.*;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    private String question;
    private LocalDate dateQst;
    @ManyToOne()
    private User userQuestions;
    @OneToMany
    private Set<Response> Responses_Du_Question;

}
