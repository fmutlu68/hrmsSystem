package furkan.hrmssystem.entities.concretes;

import java.util.Date;

import javax.persistence.*;

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
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "birth_date")
	private Date birthDate;
	
	@Column(name = "identity_no")
	private String identityNo;

	@Column(name = "e_mail")
	private String email;

	public EmployeeUser(String firstName, String lastName, Date birthDate, String identityNo, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.identityNo = identityNo;
		this.email = email;
	}
}
