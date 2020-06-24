package com.brunoshiroma.benchtool;

import com.brunoshiroma.benchtool.bench.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]){

        BenchType benchType = null;
        Bench bench = null;
        String nElementCount = null;
        int loopCount = 5;
        List<BenchResult<BigInteger>> results = new ArrayList<>();

        if(args.length >= 2){
            benchType = BenchType.fromIntValue( Integer.parseInt(args[0]) );
            nElementCount = args[1];

            if(args.length >= 3){
                loopCount = Integer.parseInt(args[2]);
            }

        }
        else{
            System.out.println("Usage java -cp . com.brunoshiroma.benchtool.Main 1 10 [5]");
            System.exit(-1);
        }


        switch (benchType){
            case Recursive:
                bench = new SimpleFibonacciRecursiveBench();
                break;
            case Loop:
                bench = new SimpleFibonacciLoopBench();
                break;
        }

        for(int i = 0; i < loopCount; i++){
            var result = bench.run(nElementCount);
            results.add(result);
        }


        final long totalRunningMs = results
                .stream()
                .mapToLong( (b) ->  b.getRunningMilliseconds() )
                .sum();

        final long averageMs = totalRunningMs / loopCount;

        BigInteger rawResult = results.get(0).result();
        final boolean resultsOk = results.stream().allMatch( (b) -> b.result().equals(rawResult));
        if(!resultsOk){
            System.out.println("All results are not ok =,(");
            System.exit(1);
        }

        System.out.println(String.format("%d %s", averageMs, rawResult));
    }

}
