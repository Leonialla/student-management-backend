package com.example.manager;

public abstract class BaseManager {

    protected int calcPageCount(int recordCount, int pageSize) {
        if (recordCount % pageSize == 0) {
            return recordCount / pageSize;
        }
        return (recordCount / pageSize) + 1;
    }
}
