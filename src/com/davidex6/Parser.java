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
            A_COMMAND_REGEX = "(^\\s*)([@])(\\w+)(\\s*)", L_COMMAND_REGEX = "(^\\s*)([(])(\\w+)([)])(\\s*)",
            EQUALS ="(^\\s*)(D|M|A)(=)(.+)", JUMP = "(^\\s*)(\\w+)(;)(\\w+)", C_COMMAND_REGEX = EQUALS + "|" + JUMP;

    private static final Pattern A_COMMAND_PATTERN = Pattern.compile(A_COMMAND_REGEX),
            L_COMMAND_PATTERN = Pattern.compile(L_COMMAND_REGEX), C_COMMAND_PATTERN = Pattern.compile(C_COMMAND_REGEX),
            EQUAL_COMMAND_PATTERN = Pattern.compile(EQUALS), JUMP_COMMAND_PATTERN = Pattern.compile(JUMP);

    private static final int A_COMMAND_SYMBOL_INDEX = 3, L_COMMAND_SYMBOL_INDEX = 3 ;

    private Scanner scanner;

    private String currentLine;

    public Parser(File file) throws FileNotFoundException {
        this.scanner = new Scanner(file);
    }

    public boolean hasMoreCommands() {
        this.readIntoCurrentLineSkippingWhitespaceAndCommentLines();
        return this.currentLine != null;
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
        if (this.currentLine.matches(L_COMMAND_REGEX)) {
            return Command.L_COMMAND;
        }
        if (this.currentLine.matches(C_COMMAND_REGEX)) {
            return Command.C_COMMAND;
        }

        return Command.A_COMMAND;
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
        return "";
    }

    public String comp() {
        return "";
    }

    public String jump() {
        String jumpSymbol = "null";


        return "";
    }
}