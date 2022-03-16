package dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StatisticConversation implements Comparable<StatisticConversation>{
    private String base;
    private String target;
    private double AverageCourse;
    private int countConversation;

    @Override
    public int compareTo(StatisticConversation o) {
        return Integer.compare(o.getCountConversation(), this.countConversation);
    }
}