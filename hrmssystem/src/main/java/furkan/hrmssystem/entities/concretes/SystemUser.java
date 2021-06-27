package furkan.hrmssystem.entities.concretes;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "system_users")
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "job_position_id")
	private int jobPositionId;
	
	@Column(name = "first_name")
	@NotNull
	@NotBlank
	private String firstName;
	
	@Column(name = "last_name")
	@NotNull
	@NotBlank
	private String lastName;
	
	@Column(name = "birth_date")
	@NotNull
	private Date birthDate;
	
	@Column(name = "identity_no")
	@NotNull
	@NotBlank
	private String identityNo;

	@Column(name = "email")
	@NotBlank
	@NotNull
	@Email
	private String email;

	@ManyToOne()
	@JoinColumn(name = "job_position_id", insertable= false, updatable = false)
	@JsonIgnore()
	private JobPosition position;
}
