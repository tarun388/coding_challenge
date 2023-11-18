import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ccwc {

    private static long byteCount(Scanner scanner) {
        long cc = 0;
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            cc += data.length() + 1;
        }
        return cc;
        // long bc = 0;
        // System.out.print("scannerd");
        // while (scanner.hasNextByte()) {
        //     System.out.print("scanner");
        //     bc += scanner.nextByte();
        //     // bc++;
        // }
        // return bc;
        // File f = new File(fileName);
        // return f.length();
    }

    private static long lineCount(Scanner scanner) {
        long lc = 0;
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            lc++;
        }
        return lc;
    }

    private static long wordCount(Scanner scanner) {
        long wc = 0;
        while (scanner.hasNext()) {
            scanner.next();
            wc++;   
        }
        return wc;
    }

    private static long charCount(Scanner scanner) {
        long cc = 0;
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            cc += data.length() + 1;
        }
        return cc;
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
                stringBuilder.append("\n");
            }
            String data = stringBuilder.toString();
            System.out.println("    " + lineCount(new Scanner(data)) + "    " + wordCount(new Scanner(data)) + "    " + byteCount(new Scanner(data)));
        }
        else if (args.length == 1) {
            if (args[0].equals("-c")) {
                System.out.println("  " + byteCount(new Scanner(System.in)));
            }
            else if (args[0].equals("-l")) {
                System.out.println("  " + lineCount(new Scanner(System.in)));
            }
            else if (args[0].equals("-w")) {
                System.out.println("  " + wordCount(new Scanner(System.in)));
            }
            else if (args[0].equals("-m")) {
                System.out.println("  " + charCount(new Scanner(System.in)));
            }
            else {
                System.out.println("    " + lineCount(new Scanner(args[0])) + "    " + wordCount(new Scanner(args[0])) + "    " + byteCount(new Scanner(args[0])) + " " + args[0]);
            }
        }
        else if (args.length == 2) {
            Scanner scanner = new Scanner(new File(args[1]));
            if (args[0].equals("-c")) {
                System.out.println("  " + byteCount(scanner) + " " + args[1]);
            }
            else if (args[0].equals("-l")) {
                System.out.println("  " + lineCount(scanner) + " " + args[1]);
            }
            else if (args[0].equals("-w")) {
                System.out.println("  " + wordCount(scanner) + " " + args[1]);
            }
            else if (args[0].equals("-m")) {
                System.out.println("  " + charCount(scanner) + " " + args[1]);
            }
            else {
                System.out.println("Invalid argument");
            }
        }
        else {
            System.out.println("No file");
        }        
    }
}