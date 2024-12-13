package doctolib;

import doctolib.model.Event;
import doctolib.model.Kind;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// You will write a function that takes a date as input and returns an object/hash
// containing the doctor's available slots for the next seven days, starting on the input date.
public class AppointmentBooking {

    // TODO. 重构时可以考虑使用Streams来过滤集合中的数据
    public List<LocalDateTime> computeSlotsForNextSevenDays(LocalDate startDate) {
        List<Event> events = new ArrayList<>();
        events.add(new Event(1, Kind.OPENING, LocalDateTime.now().plusDays(1)));
        events.add(new Event(2, Kind.APPOINTMENT, LocalDateTime.now().plusDays(1).plusHours(2)));
        events.add(new Event(3, Kind.OPENING, LocalDateTime.now().plusDays(1).plusHours(4)));
        events.add(new Event(4, Kind.OPENING, LocalDateTime.now().plusDays(2)));
        events.add(new Event(5, Kind.OPENING, LocalDateTime.now().plusDays(3)));
        events.add(new Event(6, Kind.APPOINTMENT, LocalDateTime.now().plusDays(4)));
        events.add(new Event(7, Kind.OPENING, LocalDateTime.now().plusDays(6)));
        events.add(new Event(8, Kind.OPENING, LocalDateTime.now().plusDays(8)));
        events.add(new Event(9, Kind.OPENING, LocalDateTime.now().plusDays(10)));

        return events.stream()
                .filter(event -> event.getKind() == Kind.OPENING)
                .map(Event::getLocalDateTime)
                .filter(localDateTime -> isValidSlot(localDateTime.toLocalDate(), startDate))
                .toList();

        // for (Event event: events) {
        //     LocalDate date = event.getLocalDateTime().toLocalDate();
        //     if (event.getKind() == Kind.OPENING && isValidSlot(date, startDate)) {
        //         availableSlots.add(event.getLocalDateTime());
        //     }
        // }
        // return availableSlots;
    }

    // 使用Date日期判断: 需要考虑不同国家的TimeZone问题
    private boolean isValidSlot(LocalDate eventDate, LocalDate startDate) {
         return eventDate.isAfter(startDate.minusDays(1))
                 && eventDate.isBefore(startDate.plusDays(8));
    }
}
