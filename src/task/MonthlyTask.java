package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task{
    public MonthlyTask(String heading, String description, Type type, LocalDateTime dateTime) {
        super(heading, description, type, dateTime);
    }

    @Override
    public boolean isAvailable(LocalDate inputDate) {
        LocalDate startDate = getDateTime().toLocalDate();
        while (!startDate.isAfter(inputDate)){
            if(startDate.equals(inputDate)){
                return true;
            }
            startDate = startDate.plusMonths(1);
        }
        return false;
    }
}
