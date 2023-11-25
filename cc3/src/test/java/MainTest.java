import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.verma.tarun.Compress;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;


public class MainTest {
    static String text;
    static HashMap<Character, Integer> charMap;
    static HashMap<Character, String> prefixMap;

//    @Test
//    public void test() throws IOException {
//        String fileName = "src/test/java/test1.txt";
//        // ToDo Add file check
//        Reader reader = new FileReader(fileName);
//        Compress compress = new Compress(reader);
//        compress.evaluate();
//        compress.display();
//    }

    @BeforeClass
    public static void prepare() {
        text = String.join("", Collections.nCopies(32, "C")) +
                String.join("", Collections.nCopies(42, "D")) +
                String.join("", Collections.nCopies(120, "E")) +
                String.join("", Collections.nCopies(7, "K")) +
                String.join("", Collections.nCopies(43, "L")) +
                String.join("", Collections.nCopies(24, "M")) +
                String.join("", Collections.nCopies(37, "U")) +
                String.join("", Collections.nCopies(2, "Z"));

        charMap = new HashMap<>();
        charMap.put('C', 32);
        charMap.put('D', 42);
        charMap.put('E', 120);
        charMap.put('K', 7);
        charMap.put('L', 43);
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

    @Test
    public void evaluateTest() throws IOException {
//        String testString = "aa\nbb";
        Reader reader = new StringReader(text);
        Compress compress = new Compress(reader);
//        System.out.println(compress.data);

        compress.run();
        Assert.assertEquals(charMap, compress.getCharacterHashMap());
        Assert.assertEquals(prefixMap, compress.getPrefixTable());
    }
}
