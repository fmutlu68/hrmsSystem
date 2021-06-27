package furkan.hrmssystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_operations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "operation_type_id")
    private int operationTypeId;

    @Column(name = "new_user_id")
    private int newUserId;

    @Column(name = "old_user_id")
    private int oldUserId;

    @Column(name = "activated")
    private boolean activated;

    @Column(name = "action_date")
    private Date actionDate;

    @ManyToOne()
    @JoinColumn(name = "operation_type_id", insertable = false, updatable = false)
    private OperationType operationType;

    @ManyToOne()
    @JoinColumn(name = "new_user_id", insertable = false, updatable = false)
    @JsonIgnore()
    private EmployerUser newUser;

    @ManyToOne()
    @JoinColumn(name = "old_user_id", insertable = false, updatable = false)
    @JsonIgnore()
    private EmployerUser oldUser;

    public UserOperation(int operationTypeId, int newUserId, int oldUserId, boolean activated) {
        this.operationTypeId = operationTypeId;
        this.newUserId = newUserId;
        this.oldUserId = oldUserId;
        this.activated = activated;
        this.actionDate = new Date(System.currentTimeMillis());
    }
}
