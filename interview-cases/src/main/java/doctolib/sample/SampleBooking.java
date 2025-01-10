package doctolib.sample;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SampleBooking {

    public List<LocalDateTime> getSlotsForNext7Days(LocalDate startDate) {
        List<Event> events = new ArrayList<>();
        events.add(new Event(1, Kind.OPENING, LocalDateTime.now().plusDays(1)));
        events.add(new Event(2, Kind.APPOINTMENT, LocalDateTime.now().plusDays(1).plusHours(2)));
        events.add(new Event(3, Kind.OPENING, LocalDateTime.now().plusDays(1).plusHours(4)));
        events.add(new Event(4, Kind.OPENING, LocalDateTime.now().plusDays(2)));
        events.add(new Event(5, Kind.APPOINTMENT, LocalDateTime.now().plusDays(4)));
        events.add(new Event(6, Kind.OPENING, LocalDateTime.now().plusDays(6)));

        List<LocalDateTime> availableSlots = new ArrayList<>();
        for (Event event: events) {
            LocalDate date = event.getLocalDateTime().toLocalDate();
            if (event.getKind() == Kind.OPENING && isValidSlot(date, startDate)) {
                availableSlots.add(event.getLocalDateTime());
            }
        }
        return availableSlots;
    }

    // TODO. 重构时可以考虑使用Streams来过滤集合中的数据
    public List<LocalDateTime> getSlotsForNext7DaysPlus(LocalDate startDate) {
        List<Event> events = new ArrayList<>();
        events.add(new Event(1, Kind.OPENING, LocalDateTime.now().plusDays(1)));
        events.add(new Event(2, Kind.APPOINTMENT, LocalDateTime.now().plusDays(1).plusHours(2)));
        events.add(new Event(3, Kind.OPENING, LocalDateTime.now().plusDays(1).plusHours(4)));
        events.add(new Event(4, Kind.OPENING, LocalDateTime.now().plusDays(2)));

        return events.stream()
                .filter(event -> event.getKind() == Kind.OPENING)
                .map(Event::getLocalDateTime)
                .filter(localDateTime -> isValidSlot(localDateTime.toLocalDate(), startDate))
                .toList();
    }

    // 使用Date日期判断: 需要考虑不同国家的TimeZone问题
    private boolean isValidSlot(LocalDate eventDate, LocalDate startDate) {
         return eventDate.isAfter(startDate.minusDays(1))
                 && eventDate.isBefore(startDate.plusDays(8));
    }
}
