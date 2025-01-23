package doctolib;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO. Function关键信息: 方法的功能完全取决于测试
// - SQL返回结果表示可预定的TimeStamp(Opening Event)
//   - TimeStamp 数据表存储的时刻/表示一个DateTime
//   - Slot=[TimeStamp + 15m] 定长15分钟的时间段
// - Collections.sort()排列List<>集合数据
// - StartDate起始日期不同，返回集合结果不同
//
// TODO. Unit Test单元测试 -> TDD 测试驱动开发
// - 后续测试的实现需要兼容之前测试
// - 测试对于不同StartDate起始日期的输出结果
// - 测试返回结果Slots时段的分组
// - 测试返回结果Slots合并时间(测试重点)
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

        // TODO. 遍历所有BookingEvents以创建日期和Slot时间的映射关系
        Map<String, List<String>> mapSlots = new HashMap<>();

        Map<String, List<String>> availabilities = new HashMap<>();
        for (int index=0; index < 7; index++) {
            LocalDate currentDate = startDateTime.plusDays(index).toLocalDate();
            String key = dateTimeFormatter.format(currentDate);
            List<String> slots = new ArrayList<>();

            // 创建特定某一天currentDate日期的所有Slots
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
