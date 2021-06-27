package furkan.hrmssystem.entities.concretes;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "employee_users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "first_name")
	@NotBlank
	@NotNull
	private String firstName;
	
	@Column(name = "last_name")
	@NotBlank
	@NotNull
	private String lastName;

	@Column(name = "birth_date")
	@NotNull
	private Date birthDate;
	
	@Column(name = "identity_no")
	@NotBlank
	@NotNull
	private String identityNo;

	@Column(name = "e_mail")
	@Email
	@NotBlank
	@NotNull
	private String email;

	@OneToMany(mappedBy = "user")
	@JsonIgnore()
	private List<Background> backgrounds;

	@OneToMany(mappedBy = "user")
	private List<FavoriteJobPosting> jobPostings;

	public EmployeeUser(String firstName, String lastName, Date birthDate, String identityNo, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.identityNo = identityNo;
		this.email = email;
	}
}
