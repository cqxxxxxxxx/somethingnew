package com.cqx.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * Author :chenqisheng
 * Date   :2016-09-05 21:45.
 */
public interface IExcelConsumer<T> {
    void importExcel(Workbook workbook);

    void consume(List<Sheet> sheet);

    void consume(Sheet sheet);

    boolean multi();

//    T fromRow(Row row);
}
