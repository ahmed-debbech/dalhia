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
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private int number;
    private int duration;
    private LocalDateTime dateAdded;
    private Boolean finalPhase;
    private int status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Quiz> quiz;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Resources> resources;

}
