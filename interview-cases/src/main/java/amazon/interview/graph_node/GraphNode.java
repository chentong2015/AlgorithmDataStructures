package amazon.interview.graph_node;

import java.util.List;

public class GraphNode {

    private String name;
    private float value;
    private List<GraphNode> children;

    public GraphNode(String name, float value, List<GraphNode> children) {
        this.name = name;
        this.value = value;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public List<GraphNode> getChildren() {
        return children;
    }

    public void setChildren(List<GraphNode> children) {
        this.children = children;
    }
}
