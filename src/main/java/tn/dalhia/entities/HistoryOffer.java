package tn.dalhia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HistoryOffer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Temporal(TemporalType.DATE)
    private Date date;
    private int nb;



    public HistoryOffer(String name , Date d , int nb){
        this.name= name;
        this.date= d;
        this.nb=nb;
    }
    public HistoryOffer(Long id, int nb,String name,Date d){
        this.id=id;
        this.nb=nb;
        this.name= name;
        this.date=d;

    }

}
