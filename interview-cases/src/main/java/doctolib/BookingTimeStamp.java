package doctolib;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO. Function关键信息
//- SQL返回结果表示可预定的TimeStamp(Opening Event)
//  - TimeStamp 数据表存储的时刻/表示一个DateTime
//  - Slot=[TimeStamp + 15m] 定长15分钟的时间段
//- 使用Collections.sort()排列List<>集合数据
//- 根据不同的StartDate起始日期，返回结果集合不同
public class BookingTimeStamp {

    // O(N*log(N)) 总的时间复杂度
    public List<Timestamp> processSlots(List<Timestamp> openSlots, LocalDateTime startDateTime) {
        List<Timestamp> result = new ArrayList<>();
        if (openSlots.isEmpty()) {
            return result;
        }

        for (Timestamp timestamp: openSlots) {
            LocalDateTime localDateTime = timestamp.toLocalDateTime();
            // 过滤时间戳的范围[today, today+6]表示可预定的7天范围
            if (localDateTime.isAfter(startDateTime)
                && localDateTime.isBefore(startDateTime.plusDays(7))) {
                result.add(timestamp);
            }
        }

        // 默认根据Timestamp时间戳先后进行排序
        Collections.sort(result);
        return result;
    }
}
