package com.davidex6;

import java.io.*;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Assembler {
    
    private static final String R0 = "0000000000000000", R1 = "0000000000000001", R2 = "0000000000000010",
    R3 = "0000000000000011", R4 = "0000000000000100", R5 = "0000000000000101", R6 = "0000000000000110",
    R7 = "0000000000000111", R8 = "0000000000001000", R9 = "0000000000001001", R10 = "0000000000001010",
    R11 = "0000000000001011", R12 = "0000000000001100", R13 = "0000000000001101", R14 = "0000000000001110",
    R15 = "0000000000001111", SCREEN = "0100000000000000", KBD = "0110000000000000", SP = "0000000000000000",
    LCL = "0000000000000001", ARG = "0000000000000010", THIS = "0000000000000011", THAT = "0000000000000100";
    
    private static SymbolTable symbolTable;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // init the symbol table
        initTable();

        //open buffer to write the output
        BufferedWriter buffer = buildBuffer(args[0]);

        //first pass:
        Parser parser = getParser(args[0]);
        String Lsymbol;
        int lineCounter = 0;
        parser.advance();
        String line = parser.currentLine();
        while (line != null) {
            if (parser.commandType() == Parser.Command.L_COMMAND){
                Lsymbol = parser.symbol();
                String output = Integer.toBinaryString(lineCounter);
                int rep = 16 - output.length();
                String zeros = new String(new char[rep]).replace("\0", "0");
                output = zeros + output;
                symbolTable.addEntry(Lsymbol,String.valueOf(output));
            }
            else {
                lineCounter++;
            }
            parser.advance();
            line = parser.currentLine();
        }

        //second pass:
        parser = getParser(args[0]);
        String ACsymbol = null;
        String output = null;
        int freeSpace = 16;
        parser.advance();
        line = parser.currentLine();
        while(line != null){
            //case of A-command
            if(parser.commandType() == Parser.Command.A_COMMAND){
                ACsymbol = parser.symbol();
                //case of @digit - convert it to binary 
                if (Character.isDigit(ACsymbol.charAt(0))){
                    int toInt = Integer.parseInt(ACsymbol);
                    output = Integer.toBinaryString(toInt);
                    int rep = 16 - output.length();
                    String zeros = new String(new char[rep]).replace("\0", "0");
                    output = zeros + output;

                }
                //case of @symbol
                else{
                    //if new symbol - add it to the table
                	if(!symbolTable.contains(ACsymbol)){
                        int toInt = Integer.parseInt(String.valueOf(freeSpace));
                        output = Integer.toBinaryString(toInt);
                        int rep = 16 - output.length();
                        String zeros = new String(new char[rep]).replace("\0", "0");
                        output = zeros + output;
                        symbolTable.addEntry(ACsymbol, output);
                        freeSpace++;
                    }
                    //if symbol is in the table - get the address
                	else{
                        output = symbolTable.GetAddress(ACsymbol);
                    }
                }
            }
            //case of C-command
            if(parser.commandType() == Parser.Command.C_COMMAND){
                Code code = new Code();
                output = code.comp(parser.comp()) + code.dest(parser.dest())
                        + code.jump(parser.jump());
            }
            //case of L-command - ignore it
            if (parser.commandType() == Parser.Command.L_COMMAND){
                parser.advance();
                continue;
            }
            //write to the buffer
            writeToBuffer(output, buffer);
            parser.advance();
            line = parser.currentLine();
        }
        //try to close the buffer
        try {
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /*
     * Function to initialize the symbol table with all the predefine 
     */
    private static void initTable() {
    	symbolTable = new SymbolTable();
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
	}
    
    /*
     * Build the buffer for the output file
     */
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

    /*
     * Write the output to the out file
     */
    public static void writeToBuffer(String output, BufferedWriter buffer){
        try {
            buffer.write(output);
            buffer.write(System.lineSeparator());
            buffer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Function to initialize the parser
     */
    private static Parser getParser(String fileName) throws FileNotFoundException {
        return new Parser(new File(fileName));
    }
}
