package com.brunoshiroma.benchtool.bench;

import java.math.BigInteger;

public class SimpleFibonacciRecursiveBench implements Bench {

    @Override
    public BenchResult doBenchmark(String[] args) {

        long maxInteration = Long.parseLong(args[0]);

        BigInteger result =calculateNext(BigInteger.ZERO, BigInteger.ONE, maxInteration);

        return new BenchResult(result);
    }

    private BigInteger calculateNext(BigInteger previous, BigInteger current, long maxInterations){
        return calculateNext(previous, current, maxInterations, 1);
    }

    private BigInteger calculateNext(BigInteger previous, BigInteger current, long maxInterations, long currentInteration){

        ++currentInteration;

         var result = previous.add(current);

        if(currentInteration == maxInterations){
            return result;
        }
        else{
            return calculateNext(current, result, maxInterations, currentInteration);
        }

    }

}