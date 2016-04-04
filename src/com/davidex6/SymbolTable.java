package com.davidex6; /**
 * Created by davidberiro on 4/4/16.
 */

import java.util.*;

public class SymbolTable {
    private HashMap<String, String> symbolDic;

    public SymbolTable(){
        this.symbolDic = new HashMap<String, String>();
    }

    public void addEntry(String symbol, String address){
        this.symbolDic.put(symbol, address);
    }

    public boolean contains(String symbol){
        return this.symbolDic.containsKey(symbol);
    }

    public String GetAddress(String symbol){
        return this.symbolDic.get(symbol);
    }
}
