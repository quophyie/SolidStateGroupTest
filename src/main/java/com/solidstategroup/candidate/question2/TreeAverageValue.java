package com.solidstategroup.candidate.question2;

import java.util.List;

/**
 * Class to calculate the average value of the nodes in a tree
 */
public class TreeAverageValue {

    private int totalTreeNodeCount;
    /**
     * Please implement this method to
     * return the average of all node values (Node.getValue()) in the tree.

     * @param root the root node of a tree from which to determine the average value
     * @return the average of all node values in the tree
     */
    public double getAverage(Node root) {
        double total = -1;
        if (root == null)
            return total;

        totalTreeNodeCount = 0;
        total = calcTotal(root, 0);

        return total / totalTreeNodeCount;
    }

  /**
   * Given a node, method will recursively visit every and add the node value to the
   * running total
   * @param currentNode the node under inspection
   * @param total the total value of all nodes
   * @return
   */
    private double calcTotal(Node currentNode, double total) {
        if (currentNode == null )
            return total;

        totalTreeNodeCount++;
        if (currentNode.getChildren() == null || currentNode.getChildren().isEmpty())
            return currentNode.getValue() + total;

       total += currentNode.getValue();
       int childIndex = 0;

        //Start off by getting the next node to process from current node's children
        // i.e. the 1st child and 1st child's children
        List<Node> nextNodeChildren = currentNode.getChildren().get(childIndex).getChildren();
        int nextNodeValue = currentNode.getChildren().get(childIndex).getValue();
        Node nextNode = new NodeImpl(nextNodeValue, nextNodeChildren);

        while (childIndex < currentNode.getChildren().size()) {

            // Recursively calculate the total value of the nodes
            total = calcTotal(nextNode, total);
            childIndex++;

            //Now carry on and Progress through the current node's children to get next node to process
            if (childIndex < currentNode.getChildren().size()) {
                nextNodeChildren = currentNode.getChildren().get(childIndex).getChildren();
                nextNodeValue = currentNode.getChildren().get(childIndex).getValue();
                nextNode = new NodeImpl(nextNodeValue, nextNodeChildren);
            }
        }
        return total;
    }

}
