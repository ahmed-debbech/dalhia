package tn.dalhia.entities;

import tn.dalhia.entities.enumerations.ResourceType;

import javax.persistence.*;

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
