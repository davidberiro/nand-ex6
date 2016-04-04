package com.davidex6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

public class Parser {

    public enum Command {
        A_COMMAND,
        C_COMMAND,
        L_COMMAND
    };

    private static final String COMMENT = "^\\s*//.*", WHITESPACE = "^\\s*$",
            A_COMMAND_REGEX = "(^\\s*)([@])([^\\s]+)(\\s*)", L_COMMAND_REGEX = "(^\\s*)([(])(.+)([)])(.*)",
            EQUALS ="(^\\s*)(D|M|A|AM|MD|AD)(=)([^\\s]+)(\\s*)(.*)", JUMP = "(^\\s*)(\\w+)(;)(\\w+)(\\s*)(.*)",
            C_COMMAND_REGEX = EQUALS + "|" + JUMP;

    private static final Pattern A_COMMAND_PATTERN = Pattern.compile(A_COMMAND_REGEX),
            L_COMMAND_PATTERN = Pattern.compile(L_COMMAND_REGEX),
            EQUAL_COMMAND_PATTERN = Pattern.compile(EQUALS), JUMP_COMMAND_PATTERN = Pattern.compile(JUMP);

    private static final int A_COMMAND_SYMBOL_INDEX = 3, L_COMMAND_SYMBOL_INDEX = 3, JUMP_INDEX = 4,
                            JUMP_COMP_INDEX = 2, EQUALS_COMP_INDEX = 4, EQUALS_DEST_INDEX = 2 ;

    private Scanner scanner;

    private String currentLine;

    public Parser(File file) throws FileNotFoundException {
        this.scanner = new Scanner(file);
    }

    public String currentLine() {
        return this.currentLine;
    }

    public boolean hasMoreCommands() {
        this.readIntoCurrentLineSkippingWhitespaceAndCommentLines();
        if (this.currentLine == null){
            return false;
        }
        return true;

    }

    private void readIntoCurrentLineSkippingWhitespaceAndCommentLines() {
        if (this.currentLine != null) {
            return;
        }
        while (this.scanner.hasNextLine()) {
            String line = this.scanner.nextLine();
            boolean isCommentOrWhitespace = line.matches(COMMENT) || line.matches(WHITESPACE);
            if (!isCommentOrWhitespace) {
                this.currentLine = line;
                break;
            }
        }
    }


    public void advance() {
        this.currentLine = null;
        this.readIntoCurrentLineSkippingWhitespaceAndCommentLines();
    }

    public Command commandType() {

        if (this.currentLine.matches(A_COMMAND_REGEX)) {
            return Command.A_COMMAND;
        }
        else if (this.currentLine.matches(L_COMMAND_REGEX)) {
            return Command.L_COMMAND;
        }
        return Command.C_COMMAND;

    }

    public String symbol() {
        String commandSymbol = "";
        Matcher matcher;

        matcher = A_COMMAND_PATTERN.matcher(this.currentLine);
        if (matcher.matches()){
            commandSymbol = matcher.group(A_COMMAND_SYMBOL_INDEX);
        }

        matcher = L_COMMAND_PATTERN.matcher(this.currentLine);
        if (matcher.matches()){
            commandSymbol = matcher.group(L_COMMAND_SYMBOL_INDEX);
        }
        return commandSymbol;

    }

    public String dest() {
        String destSymbol = "null";

        Matcher matcher = EQUAL_COMMAND_PATTERN.matcher(this.currentLine);
        if (matcher.matches()) {
            destSymbol = matcher.group(EQUALS_DEST_INDEX);
        }

        return destSymbol;
    }

    public String comp() {
        String compSymbol = "";

        Matcher matcher = JUMP_COMMAND_PATTERN.matcher(this.currentLine);
        if (matcher.matches()) {
            compSymbol = matcher.group(JUMP_COMP_INDEX);
        }
        matcher = EQUAL_COMMAND_PATTERN.matcher(this.currentLine);
        if (matcher.matches()) {
            compSymbol = matcher.group(EQUALS_COMP_INDEX);
        }
        return compSymbol;
    }

    public String jump() {
        String jumpSymbol = "null";
        Matcher matcher;

        matcher = JUMP_COMMAND_PATTERN.matcher(this.currentLine);
        if (matcher.matches()){
            jumpSymbol = matcher.group(JUMP_INDEX);
        }

        return jumpSymbol;
    }
}