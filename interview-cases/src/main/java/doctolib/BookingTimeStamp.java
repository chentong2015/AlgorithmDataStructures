package doctolib;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO. Function关键信息: 方法的功能完全取决于测试
//- SQL返回结果表示可预定的TimeStamp(Opening Event)
//  - TimeStamp 数据表存储的时刻/表示一个DateTime
//  - Slot=[TimeStamp + 15m] 定长15分钟的时间段
//- Collections.sort()排列List<>集合数据
//- StartDate起始日期不同，返回集合结果不同
public class BookingTimeStamp {

    public static void main(String[] args) {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 6, 10, 12, 0);
        LocalDateTime localDateTime = LocalDateTime.of(2024, 6, 10, 12, 1);
        // 必须是完全相同的时刻才会返回True
        System.out.println(localDateTime.isEqual(startDateTime));
    }

    // LocalDateTime类型拥有更加丰富的API
    public List<Timestamp> processSlots(List<Timestamp> openSlots, LocalDateTime startDateTime) {
        List<Timestamp> result = new ArrayList<>();
        if (openSlots.isEmpty()) {
            return result;
        }

        // 过滤时间戳的7天范围, 以start为起点以及之后的时间
        for (Timestamp timestamp: openSlots) {
            LocalDateTime localDateTime = timestamp.toLocalDateTime();
            if (localDateTime.isAfter(startDateTime) && localDateTime.isBefore(startDateTime.plusDays(7))) {
                result.add(timestamp);

                // 将转换后的LocalDateTime再恢复成Timestamp
                result.add(Timestamp.valueOf(localDateTime));
            }
        }
        Collections.sort(result);
        return result;
    }

    // Timestamp类型提供的API较少
    public List<Timestamp> processSlotsPlus(List<Timestamp> openSlots, Timestamp startTimestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm::ss");
        String timestampFormatted = simpleDateFormat.format(startTimestamp);

        List<Timestamp> result = new ArrayList<>();
        for (Timestamp timestamp: openSlots) {
            if (timestamp.after(startTimestamp)) {
                result.add(timestamp);
            }
        }
        return result;
    }
}
