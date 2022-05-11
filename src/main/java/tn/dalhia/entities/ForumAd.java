package tn.dalhia.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ForumAd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private int viewChannel;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime startDate;
    private Date endDate;
    private int expectedViews;
    private int actualViews; // the actual loads of the ad in a page for a user
    private float amount;
    private String imageUrl;
    private String videoUrl;
    private String text;
    private String redirectUrl;
    private int numClicks; // how many times the user clicked on the banned after loading

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "forum_ad_id")
    private ForumAdTarget forumAdTarget;

}
