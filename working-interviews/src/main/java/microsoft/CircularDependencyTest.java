package microsoft;

import java.util.HashMap;

public class CircularDependencyTest {

    private CircularDependency circularDependency = new CircularDependency();

    // 1. 测试没有任何相关联 true
    private void testCase1() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "");
        cells.put("A01", "");
        cells.put("B00", "");
        System.out.println(circularDependency.validExcelFormulas(cells));
    }

    // 2. 测试有简单关联 true
    private void testCase2() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "A01");
        cells.put("A01", "");
        cells.put("B00", "");
        System.out.println(circularDependency.validExcelFormulas(cells));
    }

    // 3. 测试有复杂关联 true
    private void testCase3() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "B00");
        cells.put("A01", "A00");
        cells.put("B00", "");
        System.out.println(circularDependency.validExcelFormulas(cells));
    }

    // 4. 测试有复杂关联 true
    private void testCase4() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "B00,B01");
        cells.put("A01", "A00");
        cells.put("B00", "");
        cells.put("B01", "");
        System.out.println(circularDependency.validExcelFormulas(cells));
    }

    // 5. 测试简单循环依赖 false
    private void testCase5() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "A01");
        cells.put("A01", "A00");
        cells.put("B00", "");
        System.out.println(circularDependency.validExcelFormulas(cells));
    }

    // 6. 测试复杂循环依赖 false
    private void testCase6() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "");
        cells.put("A01", "A00,B00,A01");
        cells.put("B00", "");
        System.out.println(circularDependency.validExcelFormulas(cells));
    }

    // 7. 测试复杂循环依赖 false
    private void testCase7() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "A01");
        cells.put("A01", "B00");
        cells.put("B00", "A00");
        System.out.println(circularDependency.validExcelFormulas(cells));
    }

    // 8. 测试复杂循环依赖 false
    private void testCase8() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "A01");
        cells.put("A01", "B00");
        cells.put("B00", "B01");
        cells.put("B01", "A01");
        System.out.println(circularDependency.validExcelFormulas(cells));
    }

    // 9. 测试复杂循环依赖 false
    private void testCase9() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "C01,A01");
        cells.put("A01", "B00");
        cells.put("B00", "B01");
        cells.put("B01", "A01");
        cells.put("C01", "");
        System.out.println(circularDependency.validExcelFormulas(cells));
    }

    // 10. 测试复杂循环依赖 false
    private void testCase10() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "C01,A01");
        cells.put("A01", "B00,D02"); // 假设这里不能出现找不到key的情况
        cells.put("B00", "B01");
        cells.put("B01", "A01");
        cells.put("C01", "");
        System.out.println(circularDependency.validExcelFormulas(cells));
    }
}
