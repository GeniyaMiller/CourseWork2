package recurrence;

import java.time.LocalDate;

public interface Recurrence {
    boolean isAvailable(LocalDate inputDate);
}
