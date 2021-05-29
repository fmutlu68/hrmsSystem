package furkan.hrmssystem.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostingDto {
    private int id;
    private String companyName;
    private String jobPositionName;
    private int vacancy;
    private Date addedDate;
    private Date deadline;
}
