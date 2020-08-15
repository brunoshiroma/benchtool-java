package com.brunoshiroma.benchtool.bench;

public abstract class Bench {

    public BenchResult run(String  ...args){
        long init = System.currentTimeMillis();
        BenchResult result = doBenchmark(args);
        long end = System.currentTimeMillis();

        result.setRunningMilliseconds(end - init);

        return result;
    }

    public abstract BenchResult doBenchmark(String ...args);

}