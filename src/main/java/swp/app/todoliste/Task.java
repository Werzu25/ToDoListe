package swp.app.todoliste;

import java.time.LocalDate;

public class Task {
    private int id;
    private String title;
    private LocalDate changeDate;

    public Task(String title, LocalDate changeDate) {
        this.title = title;
        this.changeDate = changeDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }
}
