package com.solidstategroup.candidate.question2;

import java.util.List;


public class NodeImpl implements Node {

  private int value;
  private List<Node> children;

  public NodeImpl(int value, List<Node> children){
    this.value = value;
    this.children = children;
  }
  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public List<Node> getChildren() {
    return this.children;
  }
}
