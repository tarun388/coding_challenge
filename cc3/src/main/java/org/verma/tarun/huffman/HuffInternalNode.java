package org.verma.tarun.huffman;

public class HuffInternalNode implements HuffBaseNode{
    private final int weight;
    private final HuffBaseNode left;
    private final HuffBaseNode right;

    public HuffInternalNode(int weight, HuffBaseNode left, HuffBaseNode right) {
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    public HuffBaseNode left() {
        return left;
    }

    public HuffBaseNode right() {
        return right;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public int weight() {
        return weight;
    }
}
