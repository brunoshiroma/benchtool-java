package com.brunoshiroma.benchtool.bench;

public enum BenchType{

    Loop(1),
    Recursive(2);

    private int intValue;

    BenchType(int value){
        this.intValue = value;
    }

    public static BenchType fromIntValue(int value){
        if(value == Loop.intValue){
            return Loop;
        }
        else if(value == Recursive.intValue){
            return Recursive;
        }
        else{
            throw new IllegalArgumentException(String.format("Value %s is not a benchType", value));
        }
    }

}
