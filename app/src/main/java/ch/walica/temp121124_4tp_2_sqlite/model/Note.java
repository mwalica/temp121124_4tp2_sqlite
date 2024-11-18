package ch.walica.temp121124_4tp_2_sqlite.model;

import java.time.Instant;

public class Note {
    private int id;
    private String title;
    private String description;
    private long createDate;

    public Note(int id, String title, String description, long createDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createDate = createDate;
    }

    public Note(String title, String description) {
        this.id = -1;
        this.title = title;
        this.description = description;
        this.createDate = Instant.now().getEpochSecond();
    }
}
