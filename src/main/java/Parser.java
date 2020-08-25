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
            throw new DukeException("The description of the task cannot be empty!");
        }
    }
}
