package com.davidex6;

public class Code {
    
	public String dest(String mnemonic) {
		String retVal;
		switch(mnemonic){
			case "null":
				retVal = "000";
			case "M":
				retVal = "001";
			case "D":
				retVal = "010";
			case "MD":
				retVal = "011";
			case "A":
				retVal = "100";
			case "AM":
				retVal = "101";
			case "AD":
				retVal = "110";
			case "AMD":
				retVal = "111";
			default:
				retVal = "ERROR";	
		}
		return retVal;
    }

    public String comp(String mnemonic) {
        String retVal;
    	switch(mnemonic){
        	case "0":
        		retVal = "1110101010";
        	case "1":
        		retVal = "1110111111";
        	case "-1":
        		retVal = "1110111010";
        	case "D":
        		retVal = "1110001100";
        	case "A":
        		retVal = "1110110000";
        	case "M":
        		retVal = "1111110000";
        	case "!D":
        		retVal = "1110001101";
        	case "!A":
        		retVal = "1110110001";	
        	case "!M":
        		retVal = "1111110001";
        	case "-D":
        		retVal = "1110001111";
        	case "-A":
        		retVal = "1110110011";	
        	case "-M":
        		retVal = "1111110011";
        	case "D+1":
        		retVal = "1110011111";	
        	case "A+1":
        		retVal = "1110110111";	
        	case "M+1":
        		retVal = "1111110111";
        	case "D-1":
        		retVal = "1110001110";
        	case "A-1":
        		retVal = "1110110010";
        	case "M-1":
        		retVal = "1111110010";
        	case "D+A":
        		retVal = "1110000010";
        	case "D+M":
        		retVal = "1111000010";
        	case "D-A":
        		retVal = "1110010011";	
        	case "D-M":
        		retVal = "1111010011";
        	case "A-D":
        		retVal = "1110000111";
        	case "M-D":
        		retVal = "1111000111";
        	case "D&A":
        		retVal = "1110000000";
        	case "D&M":
        		retVal = "1111000000";
        	case "D|A":
        		retVal = "1110010101";
        	case "D|M":
        		retVal = "1111010101";
        	case "A<<":
        		retVal = "1010100000";
        	case "A>>":
        		retVal = "1010000000";
        	case "D<<":
        		retVal = "1010110000";
        	case "D>>":
        		retVal = "1010010000";
        	case "M<<":
        		retVal = "1011100000";
        	case "M>>":
        		retVal = "1011000000";	
        	default:
        		retVal = "ERROR";	
        }
    	return retVal;
    }

    public String jump(String mnemonic) {
        String retVal;
    	switch(mnemonic){
    		case "null":
    			retVal = "000";
    		case "JGT":
    			retVal = "001";	
    		case "JEQ":
    			retVal = "010";
    		case "JGE":
    			retVal = "011";
    		case "JLT":
    			retVal = "100";	
    		case "JNE":
    			retVal = "101";
    		case "JLE":
    			retVal = "110";
    		case "JMP":
    			retVal = "111";	
    		default:
    			retVal = "ERROR";
        }
    	return retVal;
    }
}
