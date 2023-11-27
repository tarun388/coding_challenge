package com.verma.tarun;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class Cut {
    private final Scanner scanner;
    private final Writer writer;

    public Cut(Scanner scanner) {
        this.scanner = scanner;
        this.writer = new OutputStreamWriter(System.out);
    }

    public void cutField(List<Integer> f, String delimiter) {
        if (delimiter == null) delimiter = "\t";
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
//            log.info("Line: {}", line);

            for (Integer x: f) {
                String[] t = line.split(delimiter);
                if (t.length >= x) {
                    try {
                        writer.write(t[x-1]);
                        writer.write(delimiter);
//                    log.info(t[f-1]);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            try {
                writer.write("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
