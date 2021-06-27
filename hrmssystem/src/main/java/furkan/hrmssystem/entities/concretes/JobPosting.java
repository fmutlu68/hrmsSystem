package furkan.hrmssystem.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_postings")
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "job_description")
    @NotBlank
    @NotNull
    private String jobDescription;

    @Column(name = "max_pay")
    private int maxPay;

    @Column(name = "min_pay")
    private int minPay;

    @Column(name = "vacancy")
    private int vacancy;

    @Column(name = "pay_type")
    private String payType;

    @Column(name = "active")
    private boolean active;

    @Column(name = "activated")
    private boolean activated;

    @Column(name = "added_date")
    private Date addedDate;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "work_place")
    @NotBlank
    @NotNull
    private String workPlace;

    @Column(name = "work_time")
    @NotBlank
    @NotNull
    private String workTime;

    @ManyToOne
    @JoinColumn(name = "job_position_id")
    private JobPosition jobPosition;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private EmployerUser user;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "jobPosting")
    private List<FavoriteJobPosting> jobPostings;

}
