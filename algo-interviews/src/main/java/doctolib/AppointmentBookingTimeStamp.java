package doctolib;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentBookingTimeStamp {

    public static void main(String[] args) throws Exception {
        List<Timestamp> slots = getAllTimestamps();
        LocalDate startDate = LocalDate.now();
        for (Timestamp timestamp: slots) {
            LocalDate slotDate = timestamp.toLocalDateTime().toLocalDate();
            if (slotDate.isAfter(startDate.plusDays(2))) {
                System.out.println(timestamp);
            }
        }
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
