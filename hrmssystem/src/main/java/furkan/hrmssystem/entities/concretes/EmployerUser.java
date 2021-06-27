package furkan.hrmssystem.entities.concretes;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "employer_users")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobPostings"})
public class EmployerUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "company_name")
	@NotNull
	@NotBlank
	private String companyName;
	
	@Column(name = "company_web_site")
	private String companyWebSite;
	
	@Column(name = "company_phone")
	@NotNull
	@NotBlank
	private String companyPhone;

	@Column(name = "e_mail")
	@NotNull
	@NotBlank
	@Email
	private String email;

	@Column(name = "is_old")
	private boolean isOld;

	@OneToMany(mappedBy = "user")
	private List<JobPosting> jobPostings;

	@OneToMany(mappedBy = "newUser")
	private List<UserOperation> newOperations;

	@OneToMany(mappedBy = "oldUser")
	private List<UserOperation> oldOperations;

	public EmployerUser(String companyName, String companyWebSite, String companyPhone, String email) {
		this.companyName = companyName;
		this.companyWebSite = companyWebSite;
		this.companyPhone = companyPhone;
		this.email = email;
	}
}
