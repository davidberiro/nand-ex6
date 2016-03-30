package com.davidex6;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Main {

    private static String TEST_FILE_PATHS = Paths.get(".").toAbsolutePath().normalize().toString()
            + "/files/";

    public static void main(String[] args) throws FileNotFoundException {
        testHasMoreCommandsSkipsWhitespaceAndReturnsTrueOnNewLine();
    }

    private static void testHasMoreCommandsSkipsWhitespaceAndReturnsTrueOnNewLine() throws FileNotFoundException {
        Parser parser = new Parser(new File(TEST_FILE_PATHS + "test-reading-file"));
        System.out.println("Should have one command");
        assertTrue(parser.hasMoreCommands());
        parser.advance();
        assertTrue(!parser.hasMoreCommands());
    }

    private static void assertTrue(boolean v) {
        if (v) {
            System.out.println("Success!");
        } else {
            System.out.println("Failure!");
        }
    }
}