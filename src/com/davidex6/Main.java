package com.davidex6;

import java.io.*;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Main {

    private static String TEST_FILE_PATHS = Paths.get(".").toAbsolutePath().normalize().toString()
            + "/files/";

    private static final String R0 = "0000000000000000", R1 = "0000000000000001", R2 = "0000000000000010",
    R3 = "0000000000000011", R4 = "0000000000000100", R5 = "0000000000000101", R6 = "0000000000000110",
    R7 = "0000000000000111", R8 = "0000000000001000", R9 = "0000000000001001", R10 = "0000000000001010",
    R11 = "0000000000001011", R12 = "0000000000001100", R13 = "0000000000001101", R14 = "0000000000001110",
    R15 = "0000000000001111", SCREEN = "0100000000000000", KBD = "0110000000000000", SP = "0000000000000000",
    LCL = "0000000000000001", ARG = "0000000000000010", THIS = "0000000000000011", THAT = "0000000000000100";

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //testJump();
        //testRecognizeCommandTypes4();
        //testForSymbolMethod();
        //testRecognizeCommandTypes2();
        //testRecognizeCommandTypes1();
        //testHasMoreCommandsSkipsWhitespaceAndReturnsTrueOnNewLine();

        // init the symbol table
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.addEntry("R0", R0);
        symbolTable.addEntry("R1", R1);
        symbolTable.addEntry("R2", R2);
        symbolTable.addEntry("R3", R3);
        symbolTable.addEntry("R4", R4);
        symbolTable.addEntry("R5", R5);
        symbolTable.addEntry("R6", R6);
        symbolTable.addEntry("R7", R7);
        symbolTable.addEntry("R8", R8);
        symbolTable.addEntry("R9", R9);
        symbolTable.addEntry("R10" ,R10);
        symbolTable.addEntry("R11" ,R11);
        symbolTable.addEntry("R12" ,R12);
        symbolTable.addEntry("R13" ,R13);
        symbolTable.addEntry("R14" ,R14);
        symbolTable.addEntry("R15" ,R15);
        symbolTable.addEntry("SCREEN", SCREEN);
        symbolTable.addEntry("KBD", KBD);
        symbolTable.addEntry("SP", SP);
        symbolTable.addEntry("LCL" ,LCL);
        symbolTable.addEntry("ARG" ,ARG);
        symbolTable.addEntry("THIS" ,THIS);
        symbolTable.addEntry("THAT" ,THAT);

        //open buffer to write the output

        BufferedWriter buffer = buildBuffer(args[0]);


        File infile = new File(args[0]); //check what is the input for main

        //first pass:
        //Parser parser = new Parser(infile);
        Parser parser = getParser(args[0]);
        String Lsymbol;
        int lineCounter = 0;
        parser.advance();
        String line = parser.currentLine();
        while (line != null) {
            System.out.println(line);
            if (parser.commandType() == Parser.Command.L_COMMAND){
                Lsymbol = parser.symbol();
                symbolTable.addEntry(Lsymbol,String.valueOf(lineCounter));
            }
            else {
                lineCounter++;
            }
            parser.advance();
            line = parser.currentLine();
        }

        //second pass:
        //parser = new Parser(infile);
        parser = getParser(args[0]);
        String ACsymbol;
        String output = null;
        int freeSpace = 16;
        parser.advance();
        line = parser.currentLine();
        while(line != null){

            if(parser.commandType() == Parser.Command.A_COMMAND){
                ACsymbol = parser.symbol();
                int toInt;
                if (Character.isDigit(ACsymbol.charAt(0))){
                    toInt = Integer.parseInt(ACsymbol);
                }
                else{
                    if(!symbolTable.contains(ACsymbol)){
                        // add new symbol to the table
                        symbolTable.addEntry(ACsymbol, String.valueOf(freeSpace));
                        freeSpace++;
                    }
                    String address = symbolTable.GetAddress(ACsymbol);
                    toInt = Integer.parseInt(address);
                }
                // convert to binary and padd with zeros
                output = Integer.toBinaryString(toInt);
                int rep = 16 - output.length();
                String zeros = new String(new char[rep]).replace("\0", "0");
                output = zeros + output;
            }

            if(parser.commandType() == Parser.Command.C_COMMAND){
                Code code = new Code();
                output = "1" + code.comp(parser.comp()) + code.dest(parser.dest())
                        + code.jump(parser.jump());
            }
            // write the output to the out file
            writeToBuffer(output, buffer);

            parser.advance();
            line = parser.currentLine();
        }
        // close the buffer
        try {
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    // build the buffer for the output file
    public static BufferedWriter buildBuffer(String fileName){
        int len = fileName.length() - 4;
        String outName = fileName.substring(0,len) + ".hack";
        try {
            File outfile = new File(outName);

            outfile.createNewFile();
            FileWriter fw = new FileWriter(outfile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            return bw;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    // write the output to the out file
    public static void writeToBuffer(String output, BufferedWriter buffer){
        try {
            buffer.write(output);
            buffer.write(System.lineSeparator());
            buffer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
