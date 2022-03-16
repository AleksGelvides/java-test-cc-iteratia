package dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class StatisticConversation{
    private String base;
    private String target;
    private double AverageCourse;
    private int countConversation;
    private LocalDate startWeek;
    private LocalDate today;
}