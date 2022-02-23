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
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull private String title;
    @NonNull private Date dateAdded;
    @NonNull private Date dateRemoved;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers;
}
