package tn.dalhia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ReviewId;

    @Temporal(TemporalType.DATE)
    private Date ReviewDate;
    private String ReviewHeader;
    private String ReviewBody;
    private Float Stars;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}