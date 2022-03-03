package tn.dalhia.entities;

<<<<<<< HEAD
=======
import lombok.*;

>>>>>>> dev
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull private Long id;
    @NonNull private String address;
    @NonNull private String phone;
    @NonNull private String email;
    @NonNull private String image;
    @NonNull private LocalTime startTime;
    @NonNull private LocalTime endTime;
    @NonNull private String city;
}
