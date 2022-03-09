package tn.dalhia.entities;



import lombok.*;
import tn.dalhia.entities.enumerations.ResourceType;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ResourceType type;
    private String link;
    private String name;
    private int size;
}
