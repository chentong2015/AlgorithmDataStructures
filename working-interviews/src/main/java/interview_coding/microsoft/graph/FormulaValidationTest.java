package interview_coding.microsoft.graph;

import java.util.HashSet;

public class FormulaValidationTest {

    public static void main(String[] args) {
        Node node0 = new Node("A00", new HashSet<>());
        Node node1 = new Node("A01", new HashSet<>());
        Node node3 = new Node("A03", new HashSet<>());

        Node node5 = new Node("B05", new HashSet<>());
        Node node6 = new Node("B06", new HashSet<>());
        Node node7 = new Node("B07", new HashSet<>());

        FormulaValidation formulaValidation = new FormulaValidation();
        formulaValidation.insertMapping(node0, node1);
        formulaValidation.insertMapping(node1, node3);
        formulaValidation.insertMapping(node1, node7);
        formulaValidation.insertMapping(node3, node6);
        formulaValidation.insertMapping(node6, node5);

        System.out.println(formulaValidation.isValidFormulaForNode(node0));
    }
}
