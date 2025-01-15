package doctolib;

import java.time.LocalDateTime;

public class BookingEvent {

    long id;
    LocalDateTime startAt;
    LocalDateTime endAt;
    String kind; // 事件类型: opening, appointment

    public BookingEvent(long id, LocalDateTime startAt, LocalDateTime endAt, String kind) {
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
        this.kind = kind;
    }
}
