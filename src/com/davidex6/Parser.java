package com.davidex6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    public enum Command {
        A_COMMAND,
        C_COMMAND,
        L_COMMAND
    };

    private static final String COMMENT = "^\\s*//.*", WHITESPACE = "^\\s*$",
            A_COMMAND_REGEX = "(^\\s*)([@])(\\w+)(\\s*)()", L_COMMAND_REGEX = "(^\\s*)([(])(\\w+)([)])(\\s*)";

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

        return Command.C_COMMAND;
    }

    public String symbol() {
        return "";
    }

    public String dest() {
        return "";
    }

    public String comp() {
        return "";
    }

    public String jump() {
        return "";
    }
}