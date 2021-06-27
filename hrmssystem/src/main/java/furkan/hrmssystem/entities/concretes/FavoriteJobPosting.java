package furkan.hrmssystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "favorite_job_postings")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoriteJobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "job_posting_id")
    private int jobPostingId;

    @Column(name = "employee_user_id")
    private int userId;

    @Column(name = "added_date")
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "employee_user_id", insertable = false, updatable = false)
    @JsonIgnore
    private EmployeeUser user;

    @ManyToOne
    @JoinColumn(name = "job_posting_id", insertable = false, updatable = false)
    @JsonIgnore
    private JobPosting jobPosting;
}
