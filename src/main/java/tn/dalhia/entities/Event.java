package tn.dalhia.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import tn.dalhia.entities.enumerations.EventCategory;
import tn.dalhia.entities.enumerations.EventStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated
    @Column(name = "event_category",nullable = true)
    private EventCategory eventCategory;

    @Column(name = "title",nullable = false,length = 255)
    //@NotEmpty(message = "Title cannot be empty or null")
    private String title;

    @Column(name = "content",nullable = true)
    //@NotEmpty(message = "Content cannot be empty or null")
    private String content;

    @Column(name = "description",nullable = true)
    //@NotEmpty(message = "Description cannot be empty or null")
    private String description;

    @Column(name = "image",nullable = true)
    private String image;

    @Column(name = "start_date",nullable = true)
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "end_date",nullable = true)
    private LocalDateTime endDate;

    @Enumerated
    @Column(name = "event_status",nullable = true)
    //@NotEmpty(message = "Status cannot be empty or null")
    private EventStatus eventStatus;

    @Column(name = "is_published", nullable = true)
    private int isPublished;


    @Column(name = "views", nullable = true)
    private int views;



    @OneToMany(cascade = CascadeType.ALL)
    private List<Participation> participationList;



    @Column(name = "goal_amount")
    private double goalAmount;

    @Column(name = "collected_amount")
    private double collectedAmount;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "event", cascade = {CascadeType.ALL})
    private List<Donation> donations;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;

}
