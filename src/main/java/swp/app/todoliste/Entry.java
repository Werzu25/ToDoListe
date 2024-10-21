package swp.app.todoliste;

import java.time.LocalDate;

public class Entry {
    private String title;
    private LocalDate changeDate;

    public Entry(String title, LocalDate changeDate) {
        this.title = title;
        this.changeDate = changeDate;
    }
}
