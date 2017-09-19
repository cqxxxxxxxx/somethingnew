package com.cqx.sql_gen;


import java.io.File;
import java.io.IOException;

public interface IExcelConsumer {
    void consumer(File file, String fileName) throws IOException;
}
