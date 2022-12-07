package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnnualTask extends Task{
    public AnnualTask(String heading, String description, Type type, LocalDateTime dateTime) {
        super(heading, description, type, dateTime);
    }

    @Override
    public boolean isAvailable(LocalDate inputDate) {
        LocalDate startDate = getDateTime().toLocalDate();
        while (!startDate.isAfter(inputDate)){
            if(startDate.equals(inputDate)){
                return true;
            }
            startDate = startDate.plusYears(1);
        }
        return false;
    }
}
