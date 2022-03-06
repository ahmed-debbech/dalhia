package tn.dalhia.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.dalhia.entities.enumerations.CategoryOffer;
import tn.dalhia.entities.enumerations.JobType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "offer")
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String Description ;
    private String address;
    private String email;
    private int Level;
    private Date startDate;
    private Date endDate;



    @ManyToOne
    @JoinTable(name = "category_offer",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private JobCategory jobCategory;



    @Enumerated(EnumType.STRING)
	private JobType jobType;
    
    
    
    @OneToMany(cascade = CascadeType.ALL , mappedBy="offer")
    private List<Application> applicationList= new ArrayList<>();;



}
