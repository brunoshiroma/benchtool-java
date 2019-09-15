package com.brunoshiroma.benchtool.bench;

public class BenchResult<T> {

    private T value;

    private long runningMilliseconds;

    public BenchResult(T value){
        this.value = value;
    }

    public T result(){
        return value;
    }

    public long getRunningMilliseconds() {
        return runningMilliseconds;
    }

    public void setRunningMilliseconds(long runningMilliseconds) {
        this.runningMilliseconds = runningMilliseconds;
    }
}