package doctolib;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingTimeStampTest {

    public static void main(String[] args) {
        BookingTimeStampTest timeStampTest = new BookingTimeStampTest();
        timeStampTest.test0_empty();
        timeStampTest.test1_slot_size();
        timeStampTest.test2_first_slot();
        timeStampTest.test3_slot_order();
        timeStampTest.test4_slot_open();
    }

    private void test0_empty() {
        BookingTimeStamp bookingTimeStamp = new BookingTimeStamp();
        List<Timestamp> result = bookingTimeStamp.processSlots(new ArrayList<>());

        if (result.isEmpty()) {
            System.out.println("test 0 OK");
        } else {
            System.out.println("test 0 Failed");
        }
    }

    private void test1_slot_size() {
        BookingTimeStamp bookingTimeStamp = new BookingTimeStamp();
        List<Timestamp> openSlots = bookingTimeStamp.getOpenTimestamps();
        List<Timestamp> result = bookingTimeStamp.processSlots(openSlots);

        if (result.size() > 3) {
            System.out.println("test 1 OK");
        } else {
            System.out.println("test 1 Failed");
        }
    }

    private void test2_first_slot() {
        BookingTimeStamp bookingTimeStamp = new BookingTimeStamp();
        List<Timestamp> openSlots = bookingTimeStamp.getOpenTimestamps();
        List<Timestamp> result = bookingTimeStamp.processSlots(openSlots);

        LocalDateTime firstSlotTime = result.get(0).toLocalDateTime();
        if (firstSlotTime.isAfter(LocalDateTime.now())) {
            System.out.println("test 2 OK");
        } else {
            System.out.println("test 2 Failed");
        }
    }

    private void test3_slot_order() {
        BookingTimeStamp bookingTimeStamp = new BookingTimeStamp();
        List<Timestamp> openSlots = bookingTimeStamp.getOpenTimestamps();
        List<Timestamp> result = bookingTimeStamp.processSlots(openSlots);

        LocalDateTime firstTime = result.get(0).toLocalDateTime();
        LocalDateTime secondTime = result.get(1).toLocalDateTime();
        if (firstTime.isBefore(secondTime)) {
            System.out.println("test 3 OK");
        } else {
            System.out.println("test 3 Failed");
        }
    }

    // 测试复杂的，关于具体时刻逻辑的单元测试
    private void test4_slot_open() {
        BookingTimeStamp bookingTimeStamp = new BookingTimeStamp();
        List<Timestamp> openSlots = bookingTimeStamp.getOpenTimestamps();
        List<Timestamp> result = bookingTimeStamp.processSlots(openSlots);

    }
}
