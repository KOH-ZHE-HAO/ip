package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showList();
        int count = 1;
        for (Task task : list.getList()) {
            ui.showTask(count, task);
            count++;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}