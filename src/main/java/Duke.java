import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final String LINE_BREAK = "   ____________________________________________________________";
    private static final String SPACE_BEFORE_TEXT = "    ";
    private static final String SPACE_FOR_ADDING = "     ";

    private static boolean isInteger (String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println(LINE_BREAK);
        System.out.println(SPACE_BEFORE_TEXT + "Hey, I'm Emilia!" + "\n" +
                SPACE_BEFORE_TEXT + "What can I do for you ♥ ?");
        System.out.println(LINE_BREAK);

        while (sc.hasNext()) {
            String input = sc.nextLine().trim().toLowerCase();
            System.out.println(LINE_BREAK);
            if (input.equals("bye")) {
                System.out.println(SPACE_BEFORE_TEXT + "Welcome back, master ♥ !" + "\n" +
                        LINE_BREAK);
                break;

            }

            if (input.equals("list")) {
                System.out.println(SPACE_BEFORE_TEXT + "Please take a look at the tasks:");
                int count = 1;
                for (Task task : tasks) {
                    System.out.println(SPACE_BEFORE_TEXT + count + ". " + task);
                    count++;
                }
                System.out.println(LINE_BREAK);
                continue;
            }

            String[] process = input.split(" ", 2);
            if (process[0].equals("done") && isInteger(process[1])) {
                int index = Integer.parseInt(process[1]) - 1;
                try {
                    Task task = tasks.get(index);
                    task.markAsDone();
                    System.out.println(SPACE_BEFORE_TEXT + "Understood, I've marked this task as done:" +
                            "\n" + task);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Sorry, I can't seem to find the task...");
                }
                continue;
            }

            String firstWord = process[0];
            Task current;
            switch (firstWord) {
                case "todo":
                    current = new ToDo(process[1]);
                    break;
                case "deadline": {
                    int byIndex = process[1].indexOf(" /by ");
                    String deadline = process[1].substring(byIndex + 4).trim();
                    String description = process[1].substring(0, byIndex).trim();
                    current = new Deadline(description, deadline);
                    break;
                }
                case "event": {
                    int atIndex = process[1].indexOf(" /at ");
                    String deadline = process[1].substring(atIndex + 4).trim();
                    String description = process[1].substring(0, atIndex).trim();
                    current = new Event(description, deadline);
                    break;
                }
                default:
                    System.out.println("Invalid input detected!");
                    continue;
            }

            tasks.add(current);
            System.out.println(SPACE_BEFORE_TEXT + "Understood! I've added this task: " +
                    "\n" + SPACE_FOR_ADDING + current);
            System.out.println(SPACE_BEFORE_TEXT + "You have " + tasks.size() + " tasks in your list now!");
            System.out.println(LINE_BREAK);
        }
    }
}
