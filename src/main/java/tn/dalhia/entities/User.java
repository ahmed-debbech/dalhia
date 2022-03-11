package tn.dalhia.entities;

import tn.dalhia.entities.enumerations.AppointmentStatus;
import tn.dalhia.entities.enumerations.Job;
import tn.dalhia.entities.enumerations.ReportCategory;
import tn.dalhia.entities.enumerations.Role;
import tn.dalhia.entities.enumerations.Speciality;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import tn.dalhia.entities.enumerations.Job;
import tn.dalhia.entities.enumerations.Role;
import tn.dalhia.entities.enumerations.Speciality;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tn.dalhia.entities.enumerations.Job;
import tn.dalhia.entities.enumerations.ReportCategory;
import tn.dalhia.entities.enumerations.Role;
import tn.dalhia.entities.enumerations.Speciality;

@Getter
@Setter
@ToString

@Entity
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable=false)
    private String userId;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Column(nullable = false)
    private String first_name;
    
    @Column(nullable=false)
    private String last_name;
    
    @Column(nullable=false ,length=120,unique=true)
    private String email;
    
    @Column(nullable=false,length=9)
    private String phone;

    
    @Column(nullable=false)
    private Date date_birth;
    
   
    private String address;
    
    
    private String city;
    
    
    private String state;
    
   
    private int zipCode;
    private int start_hour; //?? time ? e.g: 19:50
    private int end_hour; //?? time ? e.g: 19:50
    @Enumerated(EnumType.STRING)
    private Job job;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    
    @Column(nullable=false)
	private String encryptedPaswword;


    @Column(nullable=false)
	private Boolean emailVerificationStatus = false;
	private String emailVerificationToken;


    @Enumerated(EnumType.STRING)
    private ReportCategory activity;
    private boolean ban;



	@OneToMany(cascade = CascadeType.ALL) //the list of courses of coach
    private List<Course> courses;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CourseProgress> courseProgresses;

    @OneToOne  //kn nheb nzid abonn maa user fard wkt
    private Subscription subscriptions; //bi
    
    @OneToMany(cascade = CascadeType.ALL ,mappedBy="users")
 	private List<Command> commands;

    //@OneToMany(cascade = CascadeType.ALL) //uni
    //private List<Application> applications;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user") //bi
    private List<Topic> topics; //?? to ask about comments

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private List<Appointment> appointmentLists;

    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private List<Request> requests;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private List<Review> reviews;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user") //bi
    private List<Report> reports;

    @OneToMany(cascade = CascadeType.ALL) //uni
    private List<Donation> donations;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Participation> participations;


    @OneToMany(mappedBy = "user")
    private List<Certificate> certificates;



    @OneToMany(cascade = CascadeType.ALL)
    private List<Application> applications;

    @OneToMany(cascade = CascadeType.ALL)
    private List<HistoryOffer> history;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    private List<Event> event = new ArrayList<>();


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }



    public int getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(int start_hour) {
        this.start_hour = start_hour;
    }

    public int getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(int end_hour) {
        this.end_hour = end_hour;
    }


    public Boolean getEmailVerificationStatus() {
        return emailVerificationStatus;
    }
    public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
    }
    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }
    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Subscription getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Subscription subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<CourseProgress> getCourseProgresses() {
        return courseProgresses;
    }

    public void setCourseProgresses(List<CourseProgress> courseProgresses) {
        this.courseProgresses = courseProgresses;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Appointment> getAppointmentLists() {
        return appointmentLists;
    }

    public void setAppointmentLists(List<Appointment> appointmentLists) {
        this.appointmentLists = appointmentLists;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public String getEncryptedPaswword() {
        return encryptedPaswword;
    }

    public void setEncryptedPaswword(String encryptedPaswword) {
        this.encryptedPaswword = encryptedPaswword;
    }

    @ManyToOne(fetch = FetchType.EAGER,optional = true)
    @JoinColumn(name = "donation_id", nullable = true)
    private Donation donation;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Application> applications;

    @OneToMany(cascade = CascadeType.ALL)
    private List<HistoryOffer> history;


}