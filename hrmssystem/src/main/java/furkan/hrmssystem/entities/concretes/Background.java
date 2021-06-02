package furkan.hrmssystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "backgrounds")
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","user"})
public class Background { //CV Anlamında Background İfadesini Kullandım.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_photo")
    private String userPhoto;

    @Column(name = "github_link")
    private String github;

    @Column(name = "linkedin_link")
    private String linkedin;

    @Column(name = "foreground")
    private String foreground;

    @OneToMany(mappedBy = "background")
    private List<JobExperience> jobExperiences;

    @OneToMany(mappedBy = "background")
    private List<LanguageExperience> languageExperiences;

    @OneToMany(mappedBy = "background")
    private List<School> schools;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private EmployeeUser user;
}
