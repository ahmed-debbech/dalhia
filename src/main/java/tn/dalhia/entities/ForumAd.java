package tn.dalhia.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ForumAd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private int viewChannel;
    private Date startDate;
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
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "forum_ad_id")
    private ForumAdTarget forumAdTarget;

}
