package tn.dalhia.entities;

import tn.dalhia.entities.enumerations.ResourceType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ResourceType type;
    private String link;
    private String name;
    private int size;
}
