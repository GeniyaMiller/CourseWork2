package service;

import task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Service {
    private Map<Integer, Task> mapTask = new HashMap<>();

    public void addTask(Task task){
        mapTask.put(task.getId(), task);

    }

    public void remove (Integer id){
        mapTask.remove(id);
    }

    public Collection<Task> getAllByDate(LocalDate inputDate){
        List<Task> result = new ArrayList<>();
        for (Map.Entry<Integer,Task> integerTaskEntry : mapTask.entrySet()){
            Task task = integerTaskEntry.getValue();
            if(task.isAvailable(inputDate)){
                result.add(task);
            }
        }
        return result;
    }
}
