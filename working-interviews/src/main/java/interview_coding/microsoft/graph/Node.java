package interview_coding.microsoft.graph;

import java.util.Set;

public class Node {

    private String key;
    private Set<Node> relyNodes;

    public Node(String key, Set<Node> relyNodes) {
        this.key = key;
        this.relyNodes = relyNodes;
    }

    public String getKey() {
        return key;
    }

    public Set<Node> getRelyNodes() {
        return relyNodes;
    }
}
