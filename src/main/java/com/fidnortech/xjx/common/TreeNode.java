package com.fidnortech.xjx.common;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {


    private String id;
    private String parentId;
    private String label;
    private Boolean leaf;
    private Object extension;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode() {
    }

    public TreeNode(String id, String label, Boolean isLeaf) {
        this.id = id;
        this.label = label;
        this.leaf = isLeaf;
    }


    public TreeNode(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public Object getExtension() {
        return extension;
    }

    public void setExtension(Object extension) {
        this.extension = extension;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public void add(TreeNode treeNode) {
        children.add(treeNode);
    }
}