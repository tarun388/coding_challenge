package org.verma.tarun.huffman;

import java.util.PriorityQueue;

public class HuffTreeBuilder {
    private PriorityQueue<HuffTree> pq;

    public HuffTreeBuilder() {
        this.pq = new PriorityQueue<>();
    }

    public HuffTree buildTree() {
        HuffTree temp1, temp2, temp3 = null;

        while (pq.size() > 1) {
            temp1 = pq.remove();
            temp2 = pq.remove();
            temp3 = new HuffTree(temp1.root(), temp2.root(), temp1.weight() + temp2.weight());

            pq.add(temp3);
        }
        return temp3;
    }

    public void add(HuffTree tree) {
        pq.add(tree);
    }
}
