package org.verma.tarun.huffman;

public class HuffTree implements Comparable {
    private final HuffBaseNode root;

    public HuffTree(char el, int wt) {
        this.root = new HuffLeafNode(el, wt);
    }

    public HuffTree(HuffBaseNode l, HuffBaseNode r, int wt) {
        this.root = new HuffInternalNode(wt, l, r);
    }

    public HuffBaseNode root() {
        return root;
    }

    public int weight() {
        return root.weight();
    }

    @Override
    public int compareTo(Object o) {
        HuffTree t = (HuffTree) o;
        if (root.weight() < t.weight()) {
            return -1;
        }
        else if (root.weight() == t.weight()) {
            return 0;
        }
        else {
            return 1;
        }
    }
}
