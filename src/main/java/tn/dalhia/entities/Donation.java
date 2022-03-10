package tn.dalhia.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "privacy", nullable = false)
    private int privacy;


    @Column(name = "donation_date")
    private LocalDateTime donationDate;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "event_id")
    private Event event;


    @Column(name = "fee_amount", nullable = false)
    private double feeAmount;

}