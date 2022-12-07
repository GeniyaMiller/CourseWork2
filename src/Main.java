import service.Service;
import task.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(service, scanner);
                            break;
                        case 2:
                            removeTask(service,scanner);
                            break;
                        case 3:
                            getAllByDate(service,scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Service service,Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        scanner.nextLine();
        System.out.println("Введите описание задачи: ");
        String description = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Введите дату создания задачи в формате дд.мм.гггг: ");
        String date = scanner.nextLine();
        LocalDate dateTask = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.println("Введите время создания задачи в формате HH:мм ");
        String time = scanner.nextLine();
        LocalTime timeTask = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime resultDateTime = LocalDateTime.of(dateTask,timeTask);
        System.out.println("Введите тип задачи: 1 = Личная, 2 = Рабочая");
        int type = scanner.nextInt();
        Type task = type == 1 ? Type.PERSONAL : Type.WORK;
        System.out.println("Введите повторяемость задачи:");
        System.out.println("0 - однократная");
        System.out.println("1 - ежедневная");
        System.out.println("2 - еженедельная");
        System.out.println("3 - ежемесячная");
        System.out.println("4 - ежегодная");
        int typeTask = scanner.nextInt();
        switch (typeTask){
            case 0:
                service.addTask(new Task(taskName,description, task, resultDateTime));
                break;
            case 1:
                service.addTask(new DailyTask(taskName,description, task, resultDateTime));
                break;
            case 2:
                service.addTask(new WeeklyTask(taskName,description, task, resultDateTime));
                break;
            case 3:
                service.addTask(new MonthlyTask(taskName,description, task, resultDateTime));
                break;
            case 4:
                service.addTask(new AnnualTask(taskName,description, task, resultDateTime));
                break;
            default:
                throw new RuntimeException("Такой задачи не существует");
        }

    }

    private static void removeTask(Service service,Scanner scanner){
        System.out.println("Введите номер задачи для удаления");
        int id = scanner.nextInt();
        service.remove(id);
    }

    private static void getAllByDate(Service service,Scanner scanner){
        System.out.println("Введите дату для поиска задач в формате дд.мм.гггг");
        scanner.nextLine();
        String date = scanner.nextLine();
        LocalDate dateTask = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        Collection<Task> allTaskByDay = service.getAllByDate(dateTask);
        System.out.println("Список задач на " + dateTask);
        for(Task task:allTaskByDay){
            System.out.println(task);
        }
    }

    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу " +
                "2. Удалить задачу " +
                "3. Получить задачу на указанный день " +
                "0. Выход "
        );
    }

}
