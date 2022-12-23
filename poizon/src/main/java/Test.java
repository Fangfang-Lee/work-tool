import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Map<String, Map<String, Object>> hbaseBatchOfflineDataMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("age", 1);
        map.put("sex", 2);
        hbaseBatchOfflineDataMap.put("132412", map);

        for (Map.Entry<String, Map<String, Object>> entry : hbaseBatchOfflineDataMap.entrySet()) {
            Map<String, Object> value = entry.getValue();
            value.remove("sex1");
        }
        System.out.println(hbaseBatchOfflineDataMap);
    }

}
