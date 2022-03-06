package tn.dalhia.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private LocalDateTime dateAdded;
    private LocalDateTime dateRemoved;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;
}
