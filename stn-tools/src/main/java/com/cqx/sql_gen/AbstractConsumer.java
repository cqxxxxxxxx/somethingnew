package com.cqx.sql_gen;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BG307435 on 2017/9/18.
 */
public abstract class AbstractConsumer implements IExcelConsumer {

    protected List<Sheet> sheets;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private Workbook createWorkBook(String fileName, InputStream is) throws IOException {
        if (fileName.toLowerCase().endsWith("xls")) {
            return new HSSFWorkbook(is);
        }
        if (fileName.toLowerCase().endsWith("xlsx")) {
            return new XSSFWorkbook(is);
        }
        return null;
    }

    public void consumer(File file, String fileName) throws IOException {
        Workbook workbook = createWorkBook(fileName, new FileInputStream(file));
        List<Sheet> sheets = new ArrayList<>();
        int size = workbook.getNumberOfSheets();
        for (int i = 0; i < size; i++) {
            sheets.add(workbook.getSheetAt(0));
        }
        this.sheets = sheets;
    }

    protected String getStringValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() != Cell.CELL_TYPE_STRING) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        return cell.getStringCellValue();
    }

    protected String getDateValue(Cell cell) {
        double d = cell.getNumericCellValue();
        return dateFormat.format(HSSFDateUtil.getJavaDate(d));
    }

    protected int getIntValue(Cell cell) {
        return (int) cell.getNumericCellValue();
    }

}
