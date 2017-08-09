package com.cqx.excel;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * Author :chenqisheng
 * Date   :2016-09-05 21:38.
 */
public class ExcelWorkSheet {
    private String sheetName;
    private List<T> data = new ArrayList<T>();
    private List<String> columns;

    public ExcelWorkSheet() {
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }
}
