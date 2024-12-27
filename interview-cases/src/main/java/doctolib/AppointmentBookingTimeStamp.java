package doctolib;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentBookingTimeStamp {

    // 需要全部通过Unit Tests单元测试
    private void testSlotsSize() {
        List<Timestamp> slots = processSlots();
        if (slots.size() != 2) {
            throw new RuntimeException("test failed");
        }
    }

    private void testFirstSlot() {
        List<Timestamp> slots = processSlots();
        LocalDateTime firstSlotTime = slots.get(0).toLocalDateTime();
        if (firstSlotTime.isAfter(LocalDateTime.now().plusDays(2))) {
            System.out.println("test success");
        } else {
            throw new RuntimeException("test failed");
        }
    }

    private void testFirstSlotHour() {
        List<Timestamp> slots = processSlots();
        int firstSlotHour = slots.get(0).toLocalDateTime().getHour();
        int nowHour = LocalDateTime.now().getHour();
        if (firstSlotHour > nowHour) {
            System.out.println("test success");
        } else {
            throw new RuntimeException("test failed");
        }
    }


    // 需要实现Functional方法逻辑: 可能使用到排序Collections.sort()方法
    public static List<Timestamp> processSlots() {
        List<Timestamp> availableSlots = new ArrayList<>();
        LocalDate startDate = LocalDate.now();
        for (Timestamp timestamp: getAllTimestamps()) {
            LocalDate slotDate = timestamp.toLocalDateTime().toLocalDate();
            if (slotDate.isAfter(startDate.plusDays(2))) {
                availableSlots.add(timestamp);
            }
        }
        return availableSlots;
    }

    // TODO. Timestamp时间戳和LocalDateTime时间相当
    // 2024-12-16 19:43:51.834134 时间戳的数据格式，包含完整的时间点
    // 2024-12-18 03:43:51.834146
    private static List<Timestamp> getAllTimestamps() {
        List<Timestamp> slots = new ArrayList<>();
        slots.add(Timestamp.valueOf(LocalDateTime.now()));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(2)));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(3).plusHours(2)));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(4).plusHours(10)));
        return slots;
    }

    // TODO. 从DB中返回时间戳用于方法过滤: 注意java类型和java.sql类型映射
    public static List<Timestamp> getTimestampsFromDB(LocalDate localDate) throws Exception {
        List<Timestamp> slotsList = new ArrayList<>();
        String query = "Select * from t_calendar where kind = 'opening' and timestamp >= ? and timestamp <= ?";
        try (Connection connection = DriverManager.getConnection("url");
             PreparedStatement statement = connection.prepareStatement(query)) {
            // The first date is inclusive, but the second is exclusive
            statement.setDate(1, Date.valueOf(localDate));
            statement.setDate(2, Date.valueOf(localDate.plusDays(8)));

            ResultSet results = statement.executeQuery();
            while (results.next()) {
                slotsList.add(results.getTimestamp("timestamp"));
            }
        }
        return slotsList;
    }
}
