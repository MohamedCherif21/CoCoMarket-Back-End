package com.example.cocomarket.Entity;

import java.time.LocalDate;
import lombok.*;

import javax.persistence.*;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    private String response;
    private LocalDate dateRps;
    private int SCORE;

}
