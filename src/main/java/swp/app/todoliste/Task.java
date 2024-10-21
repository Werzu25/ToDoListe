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
}
