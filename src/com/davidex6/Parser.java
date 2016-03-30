package com.davidex6;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Parser {

    public enum Command {
        A_COMMAND,
        C_COMMAND,
        L_COMMAND
    };

    private Scanner scanner;

    private String currentLine;

    public Parser(File file) throws FileNotFoundException {
        this.scanner = new Scanner(file);
    }

    public boolean hasMoreCommands() {
        // TODO: Think more complicated case where the next line might be a comment, etc.
        this.skipCommentOrWhitespaceLines();
        return this.scanner.hasNextLine();
    }

    private void skipCommentOrWhitespaceLines() {
        while (this.scanner.hasNextLine()) {
            String line = this.scanner.nextLine();
            boolean isCommentOrWhitespace = line.matches("^\\s*//") || line.matches("^\\s*$");
            if (!isCommentOrWhitespace) {
                this.currentLine = line;
                break;
            }
        }
    }

    public void advance() {
        this.currentLine = this.scanner.nextLine();
    }

    public Command commandType() {
        return Command.A_COMMAND;
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