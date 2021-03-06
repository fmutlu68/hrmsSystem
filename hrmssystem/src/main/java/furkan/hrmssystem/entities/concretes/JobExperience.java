package furkan.hrmssystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_experiences")
public class JobExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "workplace_name")
    private String workplaceName;

    @Column(name = "job_position")
    private String jobPosition; // Buradaki JobPosition Bizim Sistemimizdeki İş Pozisyonu Değil. Çalıştığı Yerdeki İştir. Bu Yüzden Sistemimizdeki

    @Column(name = "beginning_year")
    private String beginningYear;

    @Column(name = "ending_year")
    private String endingYear;

    @Column(name = "background_id")
    private int backgroundId;

    @ManyToOne
    @JoinColumn(name = "background_id", insertable = false, updatable = false)
    @JsonIgnore()
    private Background background; //CV Anlamında Background İfadesini Kullandım.

}
