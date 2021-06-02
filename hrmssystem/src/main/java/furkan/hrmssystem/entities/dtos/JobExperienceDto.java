package furkan.hrmssystem.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JobExperienceDto {
    private int id;
    private String workplaceName;
    private String jobPosition;
    private String userFirstName;
    private String userLastName;
    private String beginningYear;
    private String endingYear;
}
