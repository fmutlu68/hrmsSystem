package furkan.hrmssystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schools")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","background"})
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "graduation_year")
    private String graduationYear;

    @ManyToOne
    @JoinColumn(name = "background_id")
    private Background background; //CV Anlamında Background İfadesini Kullandım.
}
