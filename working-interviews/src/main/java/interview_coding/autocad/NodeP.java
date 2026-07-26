package interview_coding.autocad;

import java.util.List;

public class NodeP {

    private String key;
    private List<NodeP> connectedNodeP;
    private NodeD flowingNodeD;

    public String getKey() {
        return key;
    }

    public List<NodeP> getConnectedNodeP() {
        return connectedNodeP;
    }

    public void setFlowingNodeD(NodeD flowingNodeD) {
        this.flowingNodeD = flowingNodeD;
    }

    public NodeD getFlowingNodeD() {
        return flowingNodeD;
    }
}
