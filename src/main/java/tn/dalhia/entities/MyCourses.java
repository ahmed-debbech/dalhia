package tn.dalhia.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MyCourses {

    private List<Course> FCWithCertificates;
    private List<Course> FCWithoutCertificates;
    private List<Course> coursesInProgress;
}
