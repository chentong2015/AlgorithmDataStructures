package doctolib.sample1;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class BookingTimeStamp {

    // TODO. LocalDateTime类型拥有更丰富的API: 计算时间和时刻的偏移
    public List<Timestamp> processSlots(List<Timestamp> openSlots, LocalDateTime startDateTime) {
        List<Timestamp> result = new ArrayList<>();
        if (openSlots.isEmpty()) {
            return result;
        }

        // LocalDateTime -> Timestamp
        Timestamp timestampStart = Timestamp.valueOf(startDateTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String timestampFormatted = simpleDateFormat.format(timestampStart);

        for (Timestamp timestamp: openSlots) {
            // 考虑时刻的判断(比较Time时刻信息，小时，分钟)
            LocalDateTime tempDateTime = timestamp.toLocalDateTime();
            int hour = tempDateTime.getHour();
            int minutes = tempDateTime.getMinute();
            tempDateTime = tempDateTime.plusHours(2);
            tempDateTime = tempDateTime.plusMinutes(20);
            if (tempDateTime.isAfter(startDateTime) && tempDateTime.isBefore(startDateTime.plusDays(7))) {
                result.add(timestamp);
            }

            // 考虑日期的判断(比较Date相关信息，天，月份)
            LocalDate startDate = startDateTime.toLocalDate();
            LocalDate tempDate = tempDateTime.toLocalDate();
            if (tempDate.isEqual(startDate) && tempDate.isBefore(startDate.plusDays(7))) {
                System.out.println();
            }
        }

        // 对结果List进行排序，减少时间复杂度
        Collections.sort(result);
        return result;
    }
}
