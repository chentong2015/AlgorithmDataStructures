package interview.min_passed_time;

import java.util.Collections;
import java.util.List;

public class MinServerPassedTime {

    // 计算将servers中所有的为止遍历完成，最少需要的时间
    // total server = 8
    // servers:  2   6   8
    //            4    2   2
    // 统计每个server之间所有的间隔，最后将最大的间隔舍弃
    public static int getMinServerPassedTime(int total_servers, List<Integer> servers) {
        if (servers.size() == 1) {
            return 0;
        }
        Collections.sort(servers);

        int maxMergeTime = servers.get(1) - servers.get(0);
        int totalTime = 0;
        for (int index=1; index<servers.size(); index++) {
            int mergeTime = servers.get(index) - servers.get(index -1);
            if (mergeTime > maxMergeTime) {
                maxMergeTime = mergeTime;
            }
            totalTime += mergeTime;
        }

        int lastTime = total_servers - servers.get(servers.size() - 1) + servers.get(0);
        if (lastTime > maxMergeTime) {
            maxMergeTime = lastTime;
        }
        return totalTime + lastTime - maxMergeTime;
    }
}
