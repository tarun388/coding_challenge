import org.junit.Assert;
import org.junit.Test;
import org.verma.tarun.Compress;
import org.verma.tarun.huffman.HuffInternalNode;
import org.verma.tarun.huffman.HuffLeafNode;
import org.verma.tarun.huffman.HuffTree;
import org.verma.tarun.huffman.HuffTreeBuilder;

import java.util.HashMap;
import java.util.Queue;

public class HuffTest {
    HashMap<Character, Integer> charMap;
    HashMap<Character, String> prefixMap;

    @Test
    public void test() {
        load();
        HuffTree tree = buildTree();
        Assert.assertEquals(tree.weight(), 306);
        Assert.assertTrue(tree.root() instanceof HuffInternalNode);

        // ToDo See if you can make tree without buildTree i.e. make node and link

        Assert.assertTrue(((HuffInternalNode) tree.root()).left() instanceof HuffLeafNode);
        Assert.assertEquals('E', ((HuffLeafNode) ((HuffInternalNode) tree.root()).left()).value());

//        Assert.assertEquals(prefixMap, );
    }

    private void load() {
        charMap = new HashMap<>();
        charMap.put('C', 32);
        charMap.put('D', 42);
        charMap.put('E', 120);
        charMap.put('K', 7);
        charMap.put('L', 42);
        charMap.put('M', 24);
        charMap.put('U', 37);
        charMap.put('Z', 2);

        prefixMap = new HashMap<>();
        prefixMap.put('C', "1110");
        prefixMap.put('D', "101");
        prefixMap.put('E', "0");
        prefixMap.put('K', "111101");
        prefixMap.put('L', "110");
        prefixMap.put('M', "11111");
        prefixMap.put('U', "100");
        prefixMap.put('Z', "111100");
    }

    private HuffTree buildTree() {
        HuffTreeBuilder builder = new HuffTreeBuilder();
        for (char key: charMap.keySet()) {
            builder.add(new HuffTree(key, charMap.get(key)));
        }
        return builder.buildTree();
    }
}
