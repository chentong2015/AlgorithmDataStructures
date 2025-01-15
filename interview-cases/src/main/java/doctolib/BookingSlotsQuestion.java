package doctolib;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO. 完整测试的内容
// 1. 在单元测试中往DB Event表中插入数据
// 2. 方法中通过Select获取数据List Event
// 3. 通过List Event来创建结果的Slots时间
public class BookingSlotsQuestion {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    // 返回数据: 每半个小时记为一个有效Slot
    // 2025-01-01, {10:00, 12:00, 6:00}
    // 2025-01-05, {9:00, 9:30, 11:00}
    // 2025-01-07, {9:00, 9:30, 11:00}
    public Map<String, List<String>> getAvailabilitiesSlot(LocalDateTime startDateTime) {
        // 模拟从数据库中获取所有的事件数据
        List<BookingEvent> events = new ArrayList<>();
        events.add(new BookingEvent(1, LocalDateTime.now(), LocalDateTime.now().plusHours(1), "opening"));
        events.add(new BookingEvent(2, LocalDateTime.now(), LocalDateTime.now().plusHours(2), "appointment"));

        Map<String, List<String>> availabilities = new HashMap<>();
        for (int index=0; index < 7; index++) {
            LocalDate currentDate = startDateTime.plusDays(index).toLocalDate();
            String key = dateTimeFormatter.format(currentDate);
            List<String> slots = new ArrayList<>();

            // 处理每一天Slot的创建，将时间段添加到Value中
            for (BookingEvent event: events) {
                createSlots(slots, currentDate, event);
            }
            availabilities.put(key, slots);
        }
        return availabilities;
    }

    // TODO. 在对应的Date日期创建Slot并删除Slot时段
    private void createSlots(List<String> slots, LocalDate currentDate, BookingEvent event) {
        LocalDate eventDate = event.startAt.toLocalDate();
        LocalTime startTime = event.startAt.toLocalTime();
        LocalTime endTime = event.endAt.toLocalTime();

        if (currentDate.isEqual(eventDate)) {
            LocalTime tempTime = startTime;
            while (tempTime.isBefore(endTime)) {
                if (event.kind.equals("opening")) {
                    slots.add(timeFormatter.format(tempTime));
                } else {
                    slots.remove(timeFormatter.format(tempTime));
                }
                tempTime = tempTime.plusMinutes(30);
            }
        }
    }
}
