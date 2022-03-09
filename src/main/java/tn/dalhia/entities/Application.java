package tn.dalhia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Application implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    private String title;
    private Date date;
    private boolean etat;
    @JsonIgnore
    @ManyToOne
    @JoinTable(name = "offer_application",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id"))
    Offer offer;

    public Application (String title , Date date , boolean etat , String email){
        this.title=title;
        this.etat=etat;
        this.date=date;
        this.email=email;
    }
}
