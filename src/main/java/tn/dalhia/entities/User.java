package tn.dalhia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.dalhia.entities.enumerations.Job;
import tn.dalhia.entities.enumerations.Role;
import tn.dalhia.entities.enumerations.Speciality;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private String first_name;
    private String last_name;
    @Column(unique = true)
    private String email;
    private String phone;
    @Column(nullable = false)
    private String password;
    private Date date_birth;
    private String address;
    private String city;
    private String state;
    private int zipCode;
    private LocalTime start_hour; //?? time ? e.g: 19:50
    private LocalTime end_hour; //?? time ? e.g: 19:50
    private Job job;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @OneToMany(cascade = CascadeType.ALL) //the list of courses of coach
    private List<Course> courses;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CourseProgress> courseProgresses;

    //@OneToMany(cascade = CascadeType.ALL) //uni
    //private List<Subscription> subscriptions;

    //@OneToMany(cascade = CascadeType.ALL) //uni
    //private List<Application> applications;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user") //bi
    private List<Topic> topics; //?? to ask about comments

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user") //bi
    private List<Appointment> appointmentLists;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user") //bi
    private List<Request> requests;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "user") //bi
    //private List<Review> reviews; // ?? to ask

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user") //bi
    private List<Report> reports;

    @OneToMany(cascade = CascadeType.ALL) //uni
    private List<Donation> donations;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Participation> participations;

    @OneToMany(mappedBy = "user")
    private List<Certificate> certificates;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Application> applications;

    @OneToMany(cascade = CascadeType.ALL)
    private List<HistoryOffer> history;


}
