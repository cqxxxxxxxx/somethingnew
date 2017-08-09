package com.cqx.excel.impl.consumer;


import com.cqx.excel.ExcelUtil;
import com.cqx.excel.IExcelConsumer;
import com.cqx.excel.dao.V5AddrMapper;
import com.cqx.excel.model.V5Addr;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Author :keepcleargas
 * Date   :2017-06-05 16:08.
 */
@Component
public class V5AddrConsumer implements IExcelConsumer<V5Addr> {
    public static final Log log = LogFactory.getLog(V5AddrConsumer.class);
    public final static String SHEET_NAME = "中文";

    @Autowired
    V5AddrMapper v5AddrMapper;

    @Override
    public void importExcel(Workbook workbook) {
        List<Sheet> sheets = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        sheets.add(sheet); //中文sheet
        consume(sheets);
    }

    @Override
    public void consume(List<Sheet> sheets) {
        log.info("starting consume task...");

        List<V5Addr> v5Addrs = new ArrayList<>();

        Sheet sheet = sheets.get(0);

        if (sheet.getLastRowNum() < 1) {
            throw new IllegalArgumentException("sheet rows must bigger than 1");
        }

        int rowNum = sheet.getLastRowNum();

        for (int i = 0; i < rowNum; i ++){
            v5Addrs.add(fromRow(sheet.getRow(i)));
        }

        v5AddrMapper.batchInsert(v5Addrs);
        log.info("end consume task...");
    }

    @Override
    public void consume(Sheet sheet) {
        throw new UnsupportedOperationException("不支持单个SHEET的消费");
    }

    @Override
    public boolean multi() {
        return false;
    }

    private V5Addr fromRow(Row row){
        V5Addr v5Addr = new V5Addr();
        v5Addr.setProvince(quickStr(row, 0));
        v5Addr.setCity(quickStr(row, 1));
        v5Addr.setArea(quickStr(row, 2));

        return v5Addr;
    }

    private String quickStr(Row row, int index) {
        return ExcelUtil.getCellStringValue(row, index);
    }

}
