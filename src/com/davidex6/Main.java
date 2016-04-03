package com.davidex6;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Main {

    private static String TEST_FILE_PATHS = Paths.get(".").toAbsolutePath().normalize().toString()
            + "/files/";

    public static void main(String[] args) throws FileNotFoundException {

        testRecognizeCommandTypes2();
        testRecognizeCommandTypes1();
        testHasMoreCommandsSkipsWhitespaceAndReturnsTrueOnNewLine();


    }



    private static void testHasMoreCommandsSkipsWhitespaceAndReturnsTrueOnNewLine() throws FileNotFoundException {
        Parser parser = getParser("test-reading-file");
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

    public static void testRecognizeCommandTypes2() throws FileNotFoundException {
        Parser parser = getParser("test-reading-file2");
        parser.advance();
        parser.advance();
        assertTrue(parser.commandType().equals(Parser.Command.L_COMMAND), "Didn't get an L_COMMAND");
        parser.advance();
        assertTrue(parser.commandType().equals(Parser.Command.L_COMMAND), "Didn't get an L_COMMAND");
        parser.advance();
        assertTrue(!parser.commandType().equals(Parser.Command.A_COMMAND), "Got an L_COMMAND");
        parser.advance();
        assertTrue(parser.commandType().equals(Parser.Command.L_COMMAND), "Didn't get an L_COMMAND");

    }

    public static void testRecognizeCommandTypes1() throws FileNotFoundException {
        Parser parser = getParser("test-reading-file1");
        System.out.println("Test: Recognize command types");
        parser.advance();
        assertTrue(parser.commandType().equals(Parser.Command.A_COMMAND), "Didn't get an A_COMMAND");
        parser.advance();
        assertTrue(!parser.commandType().equals(Parser.Command.A_COMMAND), "Got an A_COMMAND");
        parser.advance();
        assertTrue(parser.commandType().equals(Parser.Command.A_COMMAND), "Didn't get an A_COMMAND");
    }

    private static void assertTrue(boolean v) {
        assertTrue(v, "");
    }

    private static void assertTrue(boolean v, String msg) {
        if (v) {
            System.out.println("Success!");
        } else {
            System.out.println("Failure! Reason: " + msg);
        }
    }

    private static Parser getParser(String fileName) throws FileNotFoundException {
        return new Parser(new File(TEST_FILE_PATHS + fileName));
    }
}
