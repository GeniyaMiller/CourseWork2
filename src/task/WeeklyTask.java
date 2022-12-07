package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    public WeeklyTask(String heading, String description, Type type, LocalDateTime dateTime) {
        super(heading, description, type, dateTime);
    }

    @Override
    public boolean isAvailable(LocalDate inputDate) {
        LocalDate startDate = getDateTime().toLocalDate();
        while (!startDate.isAfter(inputDate)){
            if(startDate.equals(inputDate)){
                return true;
            }
            startDate = startDate.plusWeeks(1);
        }
        return false;
    }
}
