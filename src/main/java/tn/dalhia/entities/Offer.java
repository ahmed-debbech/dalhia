package tn.dalhia.entities;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String title;
    private String Description ;
    private String address;
    private String email;
    private int Level;
    private Date startDate;
    private Date endDate;
    
    @Enumerated(EnumType.STRING)
	private CategoryOffer categoryOffer;
    
    @Enumerated(EnumType.STRING)
	private JobType jobType;
    
    
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Application> applicationList;



}
