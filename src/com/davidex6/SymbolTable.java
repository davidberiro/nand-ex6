package com.davidex6; 
/**
 * Created by davidberiro on 4/4/16.
 *
 * SymbolTable class:
 *		class for generate, pull and push symbols to a table.
 *		every key in the table its a symbol, and
 *		every value in the table its an address. 
 */
import java.util.*;

public class SymbolTable {
    private HashMap<String, String> symbolDic;

    //constructor
    public SymbolTable(){
        this.symbolDic = new HashMap<String, String>();
    }

    //push to the table
    public void addEntry(String symbol, String address){
        this.symbolDic.put(symbol, address);
    }
    
    //check if a given symbol is in the table
    public boolean contains(String symbol){
        return this.symbolDic.containsKey(symbol);
    }
    
    //pull from the table
    public String GetAddress(String symbol){
        return this.symbolDic.get(symbol);
    }
}
