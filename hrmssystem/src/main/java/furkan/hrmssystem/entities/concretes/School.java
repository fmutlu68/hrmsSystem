package furkan.hrmssystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "school_name")
    @NotNull
    @NotBlank
    private String schoolName;

    @Column(name = "graduation_year")
    private String graduationYear;

    @Column(name = "background_id")
    private int backgroundId;

    @ManyToOne
    @JoinColumn(name = "background_id", insertable = false, updatable = false)
    @JsonIgnore()
    private Background background; //CV Anlamında Background İfadesini Kullandım.
}
