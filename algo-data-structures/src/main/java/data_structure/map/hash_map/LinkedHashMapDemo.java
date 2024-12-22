package data_structure.map.hash_map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemo {

    // 可以设置HashMap初始容量，但HashMap会将其值优化成"二的幂次方"
    public void testLinkedHashMap() {
        Map<String, String> fields = new LinkedHashMap<>(10);
        for (int index = 0; index < 5; index++) {
            fields.put("key" + index, "value" + index);
        }
        System.out.println(fields.size());
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
