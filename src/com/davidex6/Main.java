package com.davidex6;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Main {

    private static String TEST_FILE_PATHS = Paths.get(".").toAbsolutePath().normalize().toString()
            + "/files/";

    public static void main(String[] args) throws FileNotFoundException {
        //testHasMoreCommandsSkipsWhitespaceAndReturnsTrueOnNewLine();
        testRecognizeCommandTypes();
    }

    private static void testHasMoreCommandsSkipsWhitespaceAndReturnsTrueOnNewLine() throws FileNotFoundException {
        Parser parser = new Parser(new File(TEST_FILE_PATHS + "test-reading-file"));
        System.out.println("Test: Should have one command, repeatedly calling it does not affect the result");
        assertTrue(parser.hasMoreCommands());
        assertTrue(parser.hasMoreCommands());
        assertTrue(parser.hasMoreCommands());
        parser.advance();
        assertTrue(parser.hasMoreCommands());
        parser.advance();
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

    private static void testRecognizeCommandTypes() throws FileNotFoundException {
        Parser parser = new Parser(new File(TEST_FILE_PATHS + "test-reading-file1"));
        parser.advance();
        System.out.println(parser.commandType().equals(Parser.Command.A_COMMAND));
    }
}
