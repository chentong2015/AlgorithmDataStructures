package doctolib;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO. 算法的本质: 先构建数据结构，再从数据结果中取值
public class BookingSlotsQuestionOK {

    // 模拟从数据库中获取所有的事件数据
    List<BookingEvent> events = new ArrayList<>();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    // O(N) 只遍历一次所有的Events，最佳的时间复杂度
    // O(M) 空间复杂度和存储的Date日期数量+Slot数量有关
    public Map<String, List<String>> getAvailabilitiesSlot(LocalDateTime startDateTime) {
        // TODO. 遍历BookingEvents以创建日期与Slot映射
        Map<String, List<String>> mapSlots = new HashMap<>();
        for (BookingEvent event: events) {
            processEvent(mapSlots, event);
        }

        // TODO. 直接从构建的Mapping关系中取指定的Date日期结果
        Map<String, List<String>> availabilities = new HashMap<>();
        for (int index = 0; index < 7; index++) {
            String key = buildKey(startDateTime.plusDays(index));
            List<String> slots = mapSlots.getOrDefault(key, new ArrayList<>());
            availabilities.put(key, slots);
        }
        return availabilities;
    }

    // TODO. 以下方式适用与先创建出Slot，然后删除Slot
    private void processEvent(Map<String, List<String>> mapSlots, BookingEvent event) {
        String key = buildKey(event.startAt);
        List<String> slots = mapSlots.getOrDefault(key, new ArrayList<>());

        LocalTime startTime = event.startAt.toLocalTime();
        LocalTime endTime = event.endAt.toLocalTime();
        while (startTime.isBefore(endTime)) {
            if (event.kind.equals("opening")) {
                slots.add(timeFormatter.format(startTime));
            } else {
                slots.remove(timeFormatter.format(startTime));
            }
            startTime = startTime.plusMinutes(30);
        }
        mapSlots.put(key, slots);
    }

    private String buildKey(LocalDateTime localDateTime) {
        LocalDate eventDate = localDateTime.toLocalDate();
        return dateTimeFormatter.format(eventDate);
    }
}
