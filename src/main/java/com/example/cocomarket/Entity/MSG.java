package com.example.cocomarket.Entity;
import lombok.*;

import javax.persistence.*;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MSG {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    private String body;
    private String object;
    private String msg;
    @ManyToOne(cascade = CascadeType.ALL)
    private User userMsg;
}
