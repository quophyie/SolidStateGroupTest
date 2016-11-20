package com.solidstategroup.candidate.question2;


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

        Node nextNode =  currentNode.getChildren().get(childIndex);

        while (childIndex < currentNode.getChildren().size()) {
            // Recursively calculate the total value of the nodes
            total = calcTotal(nextNode, total);
            childIndex++;

            //Now carry on and Progress through the current node's children to get next node to process
            if (childIndex < currentNode.getChildren().size()) {
               nextNode =  currentNode.getChildren().get(childIndex);
            }
        }
        return total;
    }

}
