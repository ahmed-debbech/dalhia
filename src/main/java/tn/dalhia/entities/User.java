package tn.dalhia.entities;

import tn.dalhia.entities.enumerations.AppointmentStatus;
import tn.dalhia.entities.enumerations.Job;
import tn.dalhia.entities.enumerations.Role;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int start_hour; //?? time ? e.g: 19:50
    private int end_hour; //?? time ? e.g: 19:50
    private Job job;

    @OneToMany(cascade = CascadeType.ALL) //uniderectional
    private List<Course> courses;

    @OneToMany(cascade = CascadeType.ALL) //uni
    private List<Subscription> subscriptions;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Application> applications;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Channel> channels;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Ad> ads;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Topic> topics; //?? to ask about comments

    @OneToMany(cascade = CascadeType.ALL)
    private List<Appointment> appointmentLists;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Request> requests;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews; // ?? to ask

    @OneToMany(cascade = CascadeType.ALL)
    private List<Report> reports;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Donation> donations;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Participation> participations;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Evaluation> evaluations;


}
