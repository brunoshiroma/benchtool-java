package com.brunoshiroma.benchtool.bench;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleFibonacciLoopBench implements Bench {
    @Override
    public BenchResult doBenchmark(String... args) {

        final int loopCount = Integer.parseInt(args[0]);

        BigDecimal current = new BigDecimal(0);
        BigDecimal next = new BigDecimal(1);
        BigDecimal result = new BigDecimal(0);

        for(int i = 1; i < loopCount; i++){
            result = current.add(next);

            current = next;
            next = result;
        }

        return new BenchResult(result);
    }
}
