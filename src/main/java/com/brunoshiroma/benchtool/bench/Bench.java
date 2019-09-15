package com.brunoshiroma.benchtool.bench;

public interface Bench {

    default BenchResult run(String  ...args){
        var init = System.currentTimeMillis();
        var result = doBenchmark(args);
        var end = System.currentTimeMillis();

        result.setRunningMilliseconds(end - init);

        return result;
    }

    BenchResult doBenchmark(String ...args);

}