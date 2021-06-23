package domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Course {
    public Course() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private int staffId;
    private String courseName;
    private String courseDes;
    private int credits;
    private int maxCap;
    private int duration;
    private LocalDate startDate;
    private LocalDate enrolBy;
    private LocalDate examDate;




}
