package roomescape.domain;

import java.time.LocalDate;

public class Reservation {
    private long id;
    private final String name;
    private final LocalDate date;
    private Time time;

    public Reservation(String name, LocalDate date, Time time) {
        this(0, name, date, time);
    }

    public Reservation(String name, LocalDate date, long timeId) {
        this(0, name, date, new Time(timeId, null));
    }

    public Reservation(long id, String name, LocalDate date, Time time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public boolean hasSameId(long id) {
        return this.id == id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Time getReservationTime() {
        return time;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void setTime(Time time) {
        this.time = time;
    }
}
