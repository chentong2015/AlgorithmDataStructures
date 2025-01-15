package doctolib;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingTimeStampTest {

    public static void main(String[] args) {
        BookingTimeStampTest timeStampTest = new BookingTimeStampTest();
        timeStampTest.test0_empty_slots();
        timeStampTest.test1_slot_size();
        timeStampTest.test2_first_slot();
        timeStampTest.test3_slot_order();
        timeStampTest.test4_slot_with_wrong_start_date();
    }

    // 模拟测试: 模拟从数据库中查询的所有Open Events时间戳
    private List<Timestamp> getOpenTimestamps() {
        List<Timestamp> slots = new ArrayList<>();
        slots.add(Timestamp.valueOf(LocalDateTime.now()));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(7).plusHours(4)));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(6).plusHours(5)));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(3).plusHours(2)));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(4).plusHours(6)));
        slots.add(Timestamp.valueOf(LocalDateTime.now().plusDays(2).plusMinutes(20)));
        return slots;
    }

    private void test0_empty_slots() {
        BookingTimeStamp booking = new BookingTimeStamp();
        LocalDateTime startDateTime = LocalDateTime.now();
        List<Timestamp> result = booking.processSlots(new ArrayList<>(), startDateTime);

        if (result.isEmpty()) {
            System.out.println("test 0 OK");
        } else {
            System.out.println("test 0 Failed");
        }
    }

    private void test1_slot_size() {
        BookingTimeStamp booking = new BookingTimeStamp();
        LocalDateTime startDateTime = LocalDateTime.now();
        List<Timestamp> result = booking.processSlots(getOpenTimestamps(), startDateTime);

        if (result.size() == 5) {
            System.out.println("test 1 OK");
        } else {
            System.out.println("test 1 Failed");
        }
    }

    private void test2_first_slot() {
        BookingTimeStamp booking = new BookingTimeStamp();
        LocalDateTime startDateTime = LocalDateTime.now();
        List<Timestamp> result = booking.processSlots(getOpenTimestamps(), startDateTime);

        LocalDateTime firstSlotTime = result.get(0).toLocalDateTime();
        if (firstSlotTime.isAfter(LocalDateTime.now())) {
            System.out.println("test 2 OK");
        } else {
            System.out.println("test 2 Failed");
        }
    }

    private void test3_slot_order() {
        BookingTimeStamp booking = new BookingTimeStamp();
        LocalDateTime startDateTime = LocalDateTime.now();
        List<Timestamp> result = booking.processSlots(getOpenTimestamps(), startDateTime);

        LocalDateTime firstTime = result.get(0).toLocalDateTime();
        LocalDateTime secondTime = result.get(1).toLocalDateTime();
        if (firstTime.isBefore(secondTime)) {
            System.out.println("test 3 OK");
        } else {
            System.out.println("test 3 Failed");
        }
    }

    private void test4_slot_with_wrong_start_date() {
        BookingTimeStamp booking = new BookingTimeStamp();

        LocalDateTime startDateTime = LocalDateTime.now().plusDays(8);
        List<Timestamp> result = booking.processSlots(getOpenTimestamps(), startDateTime);
        if (result.isEmpty()) {
            System.out.println("test 4 OK");
        } else {
            System.out.println("test 4 Failed");
        }
    }
}
