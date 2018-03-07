package com.cqx.sqlGen.excel;


import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IExcelConsumer {

    void consume(String path, String fileName) throws IOException;

    List parseToList(Sheet sheet) throws IllegalAccessException, InstantiationException, InvocationTargetException;
}
