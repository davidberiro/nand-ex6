package com.davidex6;

public class Code {
    
	public String dest(String mnemonic) {
        if (mnemonic.equals("null")) {
            return "000";
        }
        if (mnemonic.equals("M")) {
            return "001";
        }
        if (mnemonic.equals("D")) {
            return "010";
        }
        if (mnemonic.equals("MD")) {
            return "011";
        }
        if (mnemonic.equals("A")) {
            return "100";
        }
        if (mnemonic.equals("AM")) {
            return "101";
        }
        if (mnemonic.equals("AD")) {
            return "110";
        }
        if (mnemonic.equals("AMD")) {
            return "111";
        }
        return "ERROR";
    }

    public String comp(String mnemonic) {
        String retVal;
        if (mnemonic.equals("0")) {
            return "1110101010";
        }
        if (mnemonic.equals("1")) {
            return "1110111111";
        }
        if (mnemonic.equals("-1")) {
            return "1110111010";
        }
        if (mnemonic.equals("D")) {
            return "1110001100";
        }
        if (mnemonic.equals("A")) {
            return "1110110000";
        }
        if (mnemonic.equals("M")) {
            return "1111110000";
        }
        if (mnemonic.equals("!D")) {
            return "1110001101";
        }
        if (mnemonic.equals("!A")) {
            return "1110110001";
        }
        if (mnemonic.equals("!M")) {
            return "1111110001";
        }
        if (mnemonic.equals("-D")) {
            return "1110001111";
        }
        if (mnemonic.equals("-A")) {
            return "1110110011";
        }
        if (mnemonic.equals("-M")) {
            return "1111110011";
        }
        if (mnemonic.equals("D+1")) {
            return "1110011111";
        }
        if (mnemonic.equals("A+1")) {
            return "1110110111";
        }
        if (mnemonic.equals("M+1")) {
            return "1111110111";
        }
        if (mnemonic.equals("D-1")) {
            return "1110001110";
        }
        if (mnemonic.equals("A-1")) {
            return "1110110010";
        }
        if (mnemonic.equals("M-1")) {
            return "1111110010";
        }
        if (mnemonic.equals("D+A")) {
            return "1110000010";
        }
        if (mnemonic.equals("D+M")) {
            return "1111000010";
        }
        if (mnemonic.equals("D-A")) {
            return "1110010011";
        }
        if (mnemonic.equals("D-M")) {
            return "1111010011";
        }
        if (mnemonic.equals("A-D")) {
            return "1110000111";
        }
        if (mnemonic.equals("M-D")) {
            return "1111000111";
        }
        if (mnemonic.equals("D&A")) {
            return "1110000000";
        }
        if (mnemonic.equals("D&M")) {
            return "1111000000";
        }
        if (mnemonic.equals("D|A")) {
            return "1110010101";
        }
        if (mnemonic.equals("D|M")) {
            return "1111010101";
        }
        if (mnemonic.equals("A<<")) {
            return "1010100000";
        }
        if (mnemonic.equals("A>>")) {
            return "1010000000";
        }
        if (mnemonic.equals("D<<")) {
            return "1010110000";
        }
        if (mnemonic.equals("D>>")) {
            return "1010010000";
        }
        if (mnemonic.equals("M<<")) {
            return "1011100000";
        }
        if (mnemonic.equals("M>>")) {
            return "1011000000";
        }
        return "ERROR";
    }

    public String jump(String mnemonic) {
        if (mnemonic.equals("null")) {
            return "000";
        }
        if (mnemonic.equals("JGT")) {
            return "001";
        }
        if (mnemonic.equals("JEQ")) {
            return "010";
        }
        if (mnemonic.equals("JGE")) {
            return "011";
        }
        if (mnemonic.equals("JLT")) {
            return "100";
        }
        if (mnemonic.equals("JNE")) {
            return "101";
        }
        if (mnemonic.equals("JLE")) {
            return "110";
        }
        if (mnemonic.equals("JMP")) {
            return "111";
        }
        return "ERROR";
    }
}
