package tn.dalhia.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int points;
    private int number;
    private String text;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers;
}
