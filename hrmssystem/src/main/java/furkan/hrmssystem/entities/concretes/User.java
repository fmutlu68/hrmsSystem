package furkan.hrmssystem.entities.concretes;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

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
	@NotNull
	@NotBlank
	private String email;
	
	@Column(name = "password")
	@NotNull
	@NotBlank
	private String password;

	@Column(name = "status")
	private boolean status;

	@Column(name = "vaildaiton_code")
	@NotNull
	@NotBlank
	private String validationCode;

	public User(int userId, String email, String password) {
		this.userId = userId;
		this.email = email;
		this.password = password;
	}
	
}
