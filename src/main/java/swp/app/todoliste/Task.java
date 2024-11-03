package swp.app.todoliste;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private int id;
    private String task;
    private LocalDate changeDate;
    private boolean done;

    public Task(String task, LocalDate changeDate,boolean done) {
        this.task = task;
        this.changeDate = changeDate;
        this.done = done;
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

    public boolean isDone() {return done;}

    public void setDone(boolean done) {this.done = done;}

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return (this.id+" - "+this.task+" - due to "+this.changeDate.format(format));
    }
}
