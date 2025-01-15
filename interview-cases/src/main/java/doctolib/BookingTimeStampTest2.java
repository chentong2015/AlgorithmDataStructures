package doctolib;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingTimeStampTest2 {

    // 解析后返回结果：从早到晚，Slot间隔排布
    // 1
    // 2025-01-15 15:36:05.338119
    // 2025-01-15 16:01:05.338167
    // 2025-01-16 15:31:05.338197
    // 2025-01-18 15:31:05.338209
    // ----------
    // 2
    // 2025-01-15 15:31:05.338179
    // 2025-01-15 17:31:05.338188
    // ----------
    // 3
    // 2025-01-15 15:31:05.338215
    // 2025-01-15 15:51:05.338222
    // ----------
    // 5
    // 2025-01-15 15:37:38.84123
    // ----------
    public static void main(String[] args) {
        BookingTimeStamp booking = new BookingTimeStamp();
        Timestamp timestampStart = Timestamp.valueOf(LocalDateTime.now());
        HashMap<Long, List<Timestamp>> result = booking.processSlotsPlus(getOpenSlots(), timestampStart);

        for (Map.Entry<Long, List<Timestamp>> entry: result.entrySet()) {
            System.out.println(entry.getKey());
            for (Timestamp timestamp: entry.getValue()) {
                System.out.println(timestamp);
            }
            System.out.println("----------");
        }
    }

    // 模拟测试: 模拟从数据库中查询的所有Open Events时间戳
    private static List<Event> getOpenSlots() {
        List<Event> slots = new ArrayList<>();
        slots.add(new Event(2, Timestamp.valueOf(LocalDateTime.now().plusHours(2))));
        slots.add(new Event(1, Timestamp.valueOf(LocalDateTime.now().plusDays(1))));
        slots.add(new Event(1, Timestamp.valueOf(LocalDateTime.now().plusDays(3))));
        slots.add(new Event(3, Timestamp.valueOf(LocalDateTime.now())));
        slots.add(new Event(3, Timestamp.valueOf(LocalDateTime.now().plusMinutes(20))));
        slots.add(new Event(1, Timestamp.valueOf(LocalDateTime.now().plusMinutes(5))));
        // 属于Slot时间内的Timestamp将会被过滤
        slots.add(new Event(1, Timestamp.valueOf(LocalDateTime.now().plusMinutes(15))));
        slots.add(new Event(1, Timestamp.valueOf(LocalDateTime.now().plusMinutes(30))));
        slots.add(new Event(2, Timestamp.valueOf(LocalDateTime.now())));
        slots.add(new Event(5, Timestamp.valueOf(LocalDateTime.now().plusSeconds(100))));
        // 超过范围的Slot将被直接过滤掉
        slots.add(new Event(5, Timestamp.valueOf(LocalDateTime.now().plusDays(10))));
        return slots;
    }
}
