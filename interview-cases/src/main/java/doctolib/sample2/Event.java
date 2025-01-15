package doctolib.sample2;

import java.sql.Timestamp;

public class Event {

    public long doctorId;
    public Timestamp timestamp;

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
