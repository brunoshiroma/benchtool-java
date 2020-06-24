package com.brunoshiroma.benchtool.bench;

import java.math.BigInteger;

public class SimpleFibonacciLoopBench implements Bench {
    @Override
    public BenchResult doBenchmark(String... args) {

        final int loopCount = Integer.parseInt(args[0]);

        BigInteger current = BigInteger.ZERO;
        BigInteger next = BigInteger.ONE;
        BigInteger result = BigInteger.ZERO;

        for(int i = 1; i < loopCount; i++){
            result = current.add(next);

            current = next;
            next = result;
        }

        return new BenchResult(result);
    }
}
