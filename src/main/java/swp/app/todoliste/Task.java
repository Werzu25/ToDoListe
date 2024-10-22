package swp.app.todoliste;

import java.sql.Date;
import java.time.LocalDate;

public class Task {
    private int id;
    private String task;
    private LocalDate changeDate;

    public Task(String task, LocalDate changeDate) {
        this.task = task;
        this.changeDate = changeDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }
}