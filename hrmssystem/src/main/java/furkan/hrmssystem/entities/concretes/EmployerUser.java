package furkan.hrmssystem.entities.concretes;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "employer_users")
@NoArgsConstructor
@AllArgsConstructor
public class EmployerUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "company_web_site")
	private String companyWebSite;
	
	@Column(name = "company_phone")
	private String companyPhone;

	@Column(name = "e_mail")
	private String email;

	public EmployerUser(String companyName, String companyWebSite, String companyPhone, String email) {
		this.companyName = companyName;
		this.companyWebSite = companyWebSite;
		this.companyPhone = companyPhone;
		this.email = email;
	}
}
