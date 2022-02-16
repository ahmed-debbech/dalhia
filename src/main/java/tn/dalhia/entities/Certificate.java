package tn.dalhia.entities;

import tn.dalhia.entities.enumerations.CertificateType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Date dateAffection;
    private Date dateAdded;
    private CertificateType certificateType;
}
