package com.brunoshiroma.benchtool.bench;

import java.math.BigDecimal;

public class SimpleFibonacciRecursiveBench implements Bench {

    @Override
    public BenchResult doBenchmark(String[] args) {

        long maxInteration = Long.parseLong(args[0]);

        BigDecimal result =calculateNext(BigDecimal.valueOf(0), BigDecimal.valueOf(1), maxInteration);

        return new BenchResult(result);
    }

    private BigDecimal calculateNext(BigDecimal previous, BigDecimal current, long maxInterations){
        return calculateNext(previous, current, maxInterations, 1);
    }

    private BigDecimal calculateNext(BigDecimal previous, BigDecimal current, long maxInterations, long currentInteration){

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