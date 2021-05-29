package furkan.hrmssystem.entities.concretes;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "e_mail")
	private String eMail;
	
	@Column(name = "password")
	private String password;

	@Column(name = "status")
	private boolean status;

	@Column(name = "vaildaiton_code")
	private String validationCode;

	public User(int userId, String eMail, String password) {
		this.userId = userId;
		this.eMail = eMail;
		this.password = password;
	}
	
}
