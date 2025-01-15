package doctolib;

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

    // TODO. 解析Doctor opening slots: 分类ID + 排序时间戳 + 合并时间戳
    // - 提供从DB表格查询出来的乱数据
    // - 通过方法解析后，返回的结果能够之间被前端使用和显示
    public HashMap<Long, List<Timestamp>> processSlotsPlus(List<Event> events, Timestamp startTimestamp) {
        // TreeMap<Long, List<Timestamp>> 根据Key值自然排序
        HashMap<Long, List<Timestamp>> result = new HashMap<>();

        // 1. Filtering + Group By ID
        for (Event event : events) {
            long doctorId = event.doctorId;
            Timestamp timestamp = event.timestamp;
            LocalDateTime nextSevenDateTime = startTimestamp.toLocalDateTime().plusDays(7);
            if (timestamp.after(startTimestamp) && timestamp.toLocalDateTime().isBefore(nextSevenDateTime)) {
                if (result.containsKey(doctorId)) {
                    result.get(doctorId).add(timestamp);
                } else {
                    List<Timestamp> timestamps = new ArrayList<>();
                    timestamps.add(timestamp);
                    result.put(doctorId, timestamps);
                }
            }
        }

        // 2. Sort timestamp list
        for (Map.Entry<Long, List<Timestamp>> entry : result.entrySet()) {
            List<Timestamp> timestamps = entry.getValue();
            Collections.sort(timestamps);

            // 3. Merge timestamp opening slot
            List<Timestamp> timestampsUpdated = new ArrayList<>();
            int index = 0;
            while (index < timestamps.size()) {
                Timestamp timestamp = timestamps.get(index);
                timestampsUpdated.add(timestamp);

                // Remove next timestamp inside [timestamp, timestamp + 20m]
                LocalDateTime nextDateTime = timestamp.toLocalDateTime().plusMinutes(20);
                int nextIndex = index + 1;
                while (nextIndex < timestamps.size() && timestamps.get(nextIndex).toLocalDateTime().isBefore(nextDateTime)) {
                    nextIndex++;
                }
                // Move to the next valid timestamp
                index = nextIndex;
            }

            // Update the new entry value
            entry.setValue(timestampsUpdated);
        }
        return result;
    }

    static class Event {
        long doctorId;
        Timestamp timestamp;
    }
}
