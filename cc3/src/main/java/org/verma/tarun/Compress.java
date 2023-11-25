package org.verma.tarun;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.verma.tarun.huffman.HuffBaseNode;
import org.verma.tarun.huffman.HuffInternalNode;
import org.verma.tarun.huffman.HuffLeafNode;
import org.verma.tarun.huffman.HuffTree;
import org.verma.tarun.huffman.HuffTreeBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashMap;

@Getter
@Slf4j
public class Compress {
    private Reader reader;
    private HashMap<Character, Integer> characterHashMap;
    private HuffTree tree;
    private HashMap<Character, String> prefixTable;
    private String data;
    private String encodedString;

    public Compress(Reader reader) throws IOException {
        this.reader = reader;
        characterHashMap = new HashMap<>();
        prefixTable = new HashMap<>();
        saveData();
    }

    public void run() throws IOException {
        evaluate();
        buildTree();
        setPrefixTable(tree.root(), "");
        encode();
    }

    private void saveData() throws IOException {
        StringBuilder builder = new StringBuilder();
        int ch = reader.read();
        while (ch >= 0) {
            builder.append((char) ch);
            ch = reader.read();
        }
        data = builder.toString();
    }

    public void evaluate() throws IOException {
        reader = new StringReader(data);
        int ch = reader.read();
        while (ch >= 0) {
            if (characterHashMap.containsKey((char) ch)) {
                characterHashMap.put((char) ch, characterHashMap.get((char) ch) + 1);
            } else {
                characterHashMap.put((char) ch, 1);
            }
            ch = reader.read();
        }
    }

    public void display() {
        for (char key : characterHashMap.keySet()) {
            log.info("Character " + key + ": " + characterHashMap.get(key) + " : " + prefixTable.get(key));
        }
    }

    public void buildTree() {
        HuffTreeBuilder builder = new HuffTreeBuilder();
        for (char key : characterHashMap.keySet()) {
            builder.add(new HuffTree(key, characterHashMap.get(key)));
        }
        tree = builder.buildTree();
    }

    public void setPrefixTable(HuffBaseNode node, String pattern) {
        if (node == null) return;
        if (node instanceof HuffInternalNode) {
            setPrefixTable(((HuffInternalNode) node).left(), pattern + "0");
            setPrefixTable(((HuffInternalNode) node).right(), pattern + "1");
        } else if (node instanceof HuffLeafNode) {
            prefixTable.put(((HuffLeafNode) node).value(), pattern);
        }
    }

    private void encode() throws IOException {
        reader = new StringReader(data);
        StringBuilder builder = new StringBuilder();
        int ch = reader.read();
        while (ch >= 0) {
            builder.append(prefixTable.get((char) ch));
            ch = reader.read();
        }
        encodedString = builder.toString();
        log.info(encodedString);
    }

    public String generateHeader() {
        StringBuilder builder = new StringBuilder();
        for (char key : prefixTable.keySet()) {
            builder.append(key);
            builder.append(" ");
            builder.append(prefixTable.get(key));
            builder.append("\n");
        }
        builder.append("##");
        return builder.toString();
    }

    public String getEncodedText() {
        log.info("Converting encoded string ");
        int i = 0;
        StringBuilder builder = new StringBuilder();
        while (i < encodedString.length()) {
            String e;
            if (i + 8 < encodedString.length()) {
                e = encodedString.substring(i, i + 8);
            } else {
                e = encodedString.substring(i) + String.join("", Collections.nCopies(i + 8 - encodedString.length(), "0"));
            }
            log.info(e);
            builder.append((char) Integer.parseInt(e));
            i += 8;
        }
        return builder.toString();
    }

}

// Key Pattern
// Separator
// Text

