package furkan.hrmssystem.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessage {
    private String subject;
    private String from;
    private String to;
    private String content;
}
