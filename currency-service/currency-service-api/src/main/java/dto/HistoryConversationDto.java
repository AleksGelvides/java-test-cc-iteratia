package dto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class HistoryConversationDto{
    private long id;
    private String baseName;
    private String targetName;
    private double course;
    private LocalDate dateConversation;
}
