package tn.dalhia.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OffensiveWord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String word;
}
