package com.verma.tarun;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.Argument;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

//        File file = new File(".");
//        System.out.println(file.getAbsoluteFile());

        ArgumentParser parser = ArgumentParsers.newFor("Cut").build()
                .defaultHelp(true)
                .description("Cut");
        parser.addArgument("-f").type(String.class);
        parser.addArgument("-d").type(String.class);
        parser.addArgument("file");
        Namespace ns;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.printUsage();
            throw new RuntimeException(e);
        }

        // Sanity checks

        Scanner scanner;

        if (!ns.getString("file").isEmpty()) {
            try {
                scanner = new Scanner(new File(ns.getString("file")));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            scanner = new Scanner(System.in);
        }


        List<Integer> fields = new ArrayList<>();
        String delm = ns.getString("f").contains(",") ? "," : " ";
        for (String x: ns.getString("f").split(delm)) {
            fields.add(Integer.parseInt(x));
        }

        Cut cut = new Cut(scanner);

//        System.out.println(ns.getString("d"));
        cut.cutField(fields, ns.getString("d"));
    }
}
