package tn.dalhia.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull private String title;
    @NonNull private int number;
    @NonNull private int duration;
    @NonNull private Date dateAdded;
    @NonNull private boolean finalPhase;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Quiz> quiz;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Resources> resources;

}
