package doctolib.sample2;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class BookingTimeStamp2 {

    // TODO. 解析Doctor opening slots: 分类ID + 排序 + 合并(移除时间戳)
    // - 提供从DB表格查询出来的乱数据
    // - 通过方法解析后，返回的结果能够之间被前端使用和显示
    // 总的时间复杂度和Timestamp的数量有关
    public HashMap<Long, List<Timestamp>> processSlotsPlus(List<Event> events, Timestamp startTimestamp) {
        // TreeMap<Long, List<Timestamp>> 根据Key值自然排序
        HashMap<Long, List<Timestamp>> result = new HashMap<>();

        // 1. Filtering + Group By ID 只截取七天有效时间
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
            List<Timestamp> timestampList = entry.getValue();
            Collections.sort(timestampList);

            // 3. Refresh timestamp list
            entry.setValue(refreshTimestampList(timestampList));
        }

        // 最后输出前可以处理Timestamp的格式化问题
        return result;
    }

    // Clean timestamp inside [timestamp, timestamp + 20m]
    // 直接使用原timestampList列表来移除元素会造成O(N)时间复杂度
    private List<Timestamp> refreshTimestampList(List<Timestamp> timestampList) {
        List<Timestamp> timestampListNew = new ArrayList<>();
        int index = 0;
        while (index < timestampList.size()) {
            Timestamp timestamp = timestampList.get(index);
            timestampListNew.add(timestamp);

            LocalDateTime nextDateTime = timestamp.toLocalDateTime().plusMinutes(20);
            int nextIndex = index + 1;
            while (nextIndex < timestampList.size() && timestampList.get(nextIndex).toLocalDateTime().isBefore(nextDateTime)) {
                nextIndex++;
            }
            index = nextIndex;
        }
        return timestampListNew;
    }
}
