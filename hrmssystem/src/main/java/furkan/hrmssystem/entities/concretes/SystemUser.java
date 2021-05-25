package furkan.hrmssystem.entities.concretes;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "system_users")
public class SystemUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "job_position_id")
	private int jobPositionId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	@Column(name = "identity_no")
	private String identityNo;

	public SystemUser() {
		super();
	}

	public SystemUser(int userId, int jobPositionId, String firstName, String lastName, Date birthDate,
			String identityNo) {
		super();
		this.userId = userId;
		this.jobPositionId = jobPositionId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.identityNo = identityNo;
	}
	
	
}
