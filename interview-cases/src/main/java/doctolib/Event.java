package doctolib;

import java.sql.Timestamp;

public class Event {

    long doctorId;
    Timestamp timestamp;

    public Event(long doctorId, Timestamp timestamp) {
        this.doctorId = doctorId;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "doctorId=" + doctorId +
                ", timestamp=" + timestamp +
                '}';
    }
}
