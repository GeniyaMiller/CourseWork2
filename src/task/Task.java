package task;

import recurrence.Recurrence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


public class Task implements Recurrence {

    private String heading;
    private String description;
    private int id;
    private Type type;
    private LocalDateTime dateTime;
    private int idGenerator = 0;

    public Task(String heading, String description, Type type, LocalDateTime dateTime) {
        this.id = idGenerator++;
        if (!heading.isEmpty()|| !heading.isBlank()) {
            this.heading = heading;
        } else {
            throw new IllegalArgumentException("Введите заголовок задачи!");
        }
        if (!description.isEmpty()|| !description.isBlank()) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Введите описание задачи!");
        }
        this.type = type;
        this.dateTime = dateTime;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean isAvailable(LocalDate inputDate) {
        return inputDate.isEqual(getDateTime().toLocalDate());
    }

    @Override
    public String toString() {
        return "Задача {" +
                "id " + id +
                " Заголовок " + heading +
                ", описание " + description +
                ", тип  " + type +
                ", время " + dateTime +
                '}';
    }
}
