package duke.parser;

import duke.exception.DukeException;
import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.CheckCommand;
import duke.command.FindCommand;



public class Parser {

    /**
     * Converts user input to its associated type of Command.
     * @param input user input
     * @return specific type of Command
     * @throws DukeException if user input does not follow convention
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ByeCommand();
        }

        if (input.equals("list")) {
            return new ListCommand();
        }

        String[] process = input.split(" ", 2);

        String first = process[0].trim();

        try {
            String second = process[1].trim();
            if (first.equals("done")) {
                return new DoneCommand(second);
            }

            if (first.equals("delete")) {
                return new DeleteCommand(second);
            }

            if (first.equals("check")) {
                return new CheckCommand(second);
            }

            if (first.equals("find")) {
                return new FindCommand(second);
            }

            return new AddCommand(first, second);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Only one word detected. Remember to add whitespace" +
                    "between information or enter a valid one word command!");
        }
    }
}
