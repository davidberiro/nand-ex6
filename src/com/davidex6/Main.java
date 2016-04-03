package com.davidex6;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Main {

    private static String TEST_FILE_PATHS = Paths.get(".").toAbsolutePath().normalize().toString()
            + "/files/";

    public static void main(String[] args) throws FileNotFoundException {
        //testJump();
        //testRecognizeCommandTypes4();
        //testForSymbolMethod();
        testRecognizeCommandTypes2();
        //testRecognizeCommandTypes1();
        //testHasMoreCommandsSkipsWhitespaceAndReturnsTrueOnNewLine();


    }

    public static void testRecognizeCommandTypes4() throws FileNotFoundException {
        Parser parser = getParser("test-reading-file4");
        parser.advance();
        assertTrue(!parser.commandType().equals(Parser.Command.C_COMMAND), "Got an C_COMMAND");
        parser.advance();
        assertTrue(parser.commandType().equals(Parser.Command.C_COMMAND), "Didn't get an C_COMMAND");
        parser.advance();
        assertTrue(!parser.commandType().equals(Parser.Command.C_COMMAND), "Got an C_COMMAND");
        parser.advance();
        assertTrue(!parser.commandType().equals(Parser.Command.C_COMMAND), "Got an C_COMMAND");
        parser.advance();
        assertTrue(parser.commandType().equals(Parser.Command.C_COMMAND), "Didn't get an C_COMMAND");

    }

    private static void testJump() throws FileNotFoundException {
        Parser parser = getParser("jump_tester");
        parser.advance();
        System.out.println(parser.jump());
        System.out.println(parser.comp());
        parser.advance();
        System.out.println(parser.jump());
        System.out.println(parser.comp());
    }

    private static void testForSymbolMethod() throws FileNotFoundException {
        Parser parser = getParser("test-reading-file3");
        parser.advance();
        System.out.println(parser.symbol());
        parser.advance();
        System.out.println(parser.symbol());
        parser.advance();
        System.out.println(parser.symbol());
        parser.advance();
        System.out.println(parser.symbol());
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
