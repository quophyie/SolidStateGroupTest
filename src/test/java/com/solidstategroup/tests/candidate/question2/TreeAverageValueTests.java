package com.solidstategroup.tests.candidate.question2;

import com.solidstategroup.candidate.question2.Node;
import com.solidstategroup.candidate.question2.NodeImpl;
import com.solidstategroup.candidate.question2.TreeAverageValue;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TreeAverageValueTests {

  private TreeAverageValue treeAverageValue;
  private Node root, leftNodeOfRoot, rightNodeOfRoot, leftLeafOfLeftNodeOfRoot;

  @Before
  public void setUp() {
    treeAverageValue = new TreeAverageValue();

    // Constructing binary tree
    leftLeafOfLeftNodeOfRoot = new NodeImpl(4, null);
    Node rightLeafOfLeftNodeOfRoot = new NodeImpl(5, null);

    Node leftLeafOfRightNodeOfRoot = new NodeImpl(6, null);
    Node rightLeafOfRightNodeOfRoot = new NodeImpl(7, null);

    List<Node> leftNodeRootChildren = new ArrayList<Node>();
    leftNodeRootChildren.add(leftLeafOfLeftNodeOfRoot);
    leftNodeRootChildren.add(rightLeafOfLeftNodeOfRoot);
    leftNodeOfRoot = new NodeImpl(2, leftNodeRootChildren);

    List<Node> rightNodeRootChildren  = new ArrayList<Node>();
    rightNodeRootChildren.add(leftLeafOfRightNodeOfRoot);
    rightNodeRootChildren.add(rightLeafOfRightNodeOfRoot);
    rightNodeOfRoot = new NodeImpl(3, rightNodeRootChildren);

    List<Node>  rootChildren =  new ArrayList<Node>();
    rootChildren.add(leftNodeOfRoot);
    rootChildren.add(rightNodeOfRoot);

    //The root forms the root of binary tree constructed above
    root = new NodeImpl(1, rootChildren);

  }

  @Test
  public void shouldReturnNegative1GivenANullNode(){
    double result = treeAverageValue.getAverage(null);
    assertEquals(-1.0, result);
  }

  @Test
  public void shouldReturnTheNodeValueGivenNodeWithNoChildren() {

    double result = treeAverageValue.getAverage(leftLeafOfLeftNodeOfRoot);
    assertEquals(4.0, result);
  }

  @Test
  public void shouldReturnTheAverageGivenNodeWith2LeafChildren() {

    double result = treeAverageValue.getAverage(leftNodeOfRoot);
    assertEquals(3.6666666666666665, result);
  }

  @Test
  public void shouldReturnTheAverageGivenBinaryTreeStartingFromRoot() {

    double result = treeAverageValue.getAverage(root);
    assertEquals(4.0, result);
  }

  @Test
  public void shouldReturnTheAverageGivenTreeWhereLeftNodeOfRootNodeHasThreeLeafChildren() {

    Node centreLeafOfLeftNodeOfRoot = new NodeImpl(8, null);
    root.getChildren().get(0).getChildren().add(centreLeafOfLeftNodeOfRoot);

    double result = treeAverageValue.getAverage(root);
    assertEquals(4.5, result);
  }

  @Test
  public void shouldReturnTheAverageGivenTreeWhereLeftAndRightNodeOfRootNodeHaveThreeLeafChildrenEach() {

    Node centreLeafOfLeftNodeOfRoot = new NodeImpl(8, null);
    Node centreLeafOfRightNodeOfRoot = new NodeImpl(9, null);
    root.getChildren().get(0).getChildren().add(centreLeafOfLeftNodeOfRoot);
    root.getChildren().get(0).getChildren().add(centreLeafOfRightNodeOfRoot);

    double result = treeAverageValue.getAverage(root);
    assertEquals(5.0, result);
  }

  @Test
  public void shouldReturnTheAverageGivenTreeWhereRootHas3ChildrenAndEachChildOfTheRootNodeHasThreeLeafChildren() {

    Node leftLeafOfCentreChildOfRootNode = new NodeImpl(10, null);
    Node centreLeafOfCentreChildOfRootNode = new NodeImpl(11, null);
    Node rightLeafOfCentreChildOfRootNode = new NodeImpl(12, null);
    List<Node> centreChildOfRootNodeChildren = new ArrayList<Node>();

    centreChildOfRootNodeChildren.add(leftLeafOfCentreChildOfRootNode);
    centreChildOfRootNodeChildren.add(centreLeafOfCentreChildOfRootNode);
    centreChildOfRootNodeChildren.add(rightLeafOfCentreChildOfRootNode);

    Node centreChildOfRootNode = new NodeImpl(13, centreChildOfRootNodeChildren);


    Node centreLeafOfLeftNodeOfRoot = new NodeImpl(8, null);
    Node centreLeafOfRightNodeOfRoot = new NodeImpl(9, null);

    root.getChildren().add(centreChildOfRootNode);
    root.getChildren().get(0).getChildren().add(centreLeafOfLeftNodeOfRoot);
    root.getChildren().get(0).getChildren().add(centreLeafOfRightNodeOfRoot);

    double result = treeAverageValue.getAverage(root);
    assertEquals(7.0, result);
  }


}
