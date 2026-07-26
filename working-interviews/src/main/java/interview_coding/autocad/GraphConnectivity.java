package interview_coding.autocad;

import java.util.ArrayList;
import java.util.List;

public class GraphConnectivity {

    public static void main(String[] args) {
        NodeP nodeP1 = new NodeP();
        NodeD nodeD1 = new NodeD();
        NodeM nodeM1 = new NodeM();
        nodeP1.setFlowingNodeD(nodeD1);
        nodeD1.setFollowingNodeM(nodeM1);

        // 同类型的数据应该具有共同的属性
        List<Object> listNodeS1 = new ArrayList<>();
        listNodeS1.add(nodeP1);
        listNodeS1.add(nodeD1);
        listNodeS1.add(nodeM1);
    }
}
