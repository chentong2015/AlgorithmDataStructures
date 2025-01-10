package doctolib;

import java.sql.*;
import java.text.CollationKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingTimeStamp {

    // 从数据库中返回的所有Open Events时间戳，需要进行解析和筛选
    public List<Timestamp> getOpenTimestamps() {
        List<Timestamp> slots = new ArrayList<>();
        slots.add(Timestamp.valueOf(LocalDateTime.now()));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(7).plusHours(4)));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(6).plusHours(5)));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(3).plusHours(2)));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(4).plusHours(6)));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(2)));
        return slots;
    }

    // O(N*log(N)) 总的时间复杂度
    public List<Timestamp> processSlots(List<Timestamp> openSlots) {
        if (openSlots.isEmpty()) {
            return new ArrayList<>();
        }

        List<Timestamp> result = new ArrayList<>();
        for (Timestamp timestamp: openSlots) {
            LocalDateTime localDateTime = timestamp.toLocalDateTime();
            if (localDateTime.isAfter(LocalDateTime.now())
            && localDateTime.isBefore(LocalDateTime.now().plusDays(8))) {
                result.add(timestamp);
            }
        }

        // 对筛选后的结果进行排序，优化时间复杂度 O(M*log(M))
        // 默认根据Timestamp时间戳先后进行排序
        Collections.sort(result);
        return result;
    }
}
