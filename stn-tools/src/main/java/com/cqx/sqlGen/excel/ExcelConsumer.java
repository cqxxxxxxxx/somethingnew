package com.cqx.sqlGen.excel;

import com.cqx.gen.ClazzContext;
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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by BG307435 on 2017/9/21.
 */
public class ExcelConsumer implements IExcelConsumer {

    private List<Sheet> sheets;
    private ClazzContext clazzContext;
    private DateFormat dateFormat;

    /**
     * sheet解析到参数对象的list
     *
     * @param sheet
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public List parseToList(Sheet sheet) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Row row;
        Cell cell;
        Method method;
        final Map<Integer, Method> setMethodsMap = clazzContext.getSetMethodsMap();
        final int rowNum = sheet.getLastRowNum();
        List<Object> results = new ArrayList<>(1024);
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            Object obj = clazzContext.getClazz().newInstance();
            for (Map.Entry<Integer, Method> entry : setMethodsMap.entrySet()) {
                Integer index = entry.getKey();
                cell = row.getCell(index);
                method = entry.getValue();
                Class type = method.getParameterTypes()[0];
                //调用set方法 注入值
                if (type == String.class) {
                    method.invoke(obj, getStringValue(cell));
                } else if (type == Date.class) {
                    method.invoke(obj, getDateValue(cell));
                } else {
                    method.invoke(obj, getIntValue(cell));
                }
            }
            results.add(obj);
        }
        return results;
    }


    public void consume(String path) throws IOException {
        consume(path, path);
    }

    /**
     * 解析excel 生成sheets
     *
     * @param path
     * @param fileName
     * @throws IOException
     */
    public void consume(String path, String fileName) throws IOException {
        File file = new File(path);
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

    private Workbook createWorkBook(String fileName, InputStream is) throws IOException {
        if (fileName.toLowerCase().endsWith("xls")) {
            return new HSSFWorkbook(is);
        }
        if (fileName.toLowerCase().endsWith("xlsx")) {
            return new XSSFWorkbook(is);
        }
        return null;
    }

    public List<Sheet> getSheets() {
        return sheets;
    }

    public void setClazzContext(ClazzContext clazzContext) {
        this.clazzContext = clazzContext;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

}
