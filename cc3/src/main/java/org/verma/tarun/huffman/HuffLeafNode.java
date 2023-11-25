package org.verma.tarun.huffman;

public class HuffLeafNode implements HuffBaseNode {
    private final char element;
    private final int weight;

    public HuffLeafNode(char element, int weight) {
        this.element = element;
        this.weight = weight;
    }

    public char value() {
        return element;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public int weight() {
        return weight;
    }
}
