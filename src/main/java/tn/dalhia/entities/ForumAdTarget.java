package tn.dalhia.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ForumAdTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int ageMax;
    private int ageMin;
    private int donationCount; // the donation made count
    private int applicationCount; // the ad shows to who has the application to offers
    private int certCount; // the ad shows to who has a certificate equal to that number or more
    private boolean any; // any criteria

}
