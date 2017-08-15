package com.cqx.excel.impl.consumer;

import com.cqx.excel.ExcelUtil;
import com.cqx.excel.IExcelConsumer;
import com.cqx.excel.dao.StandardMapper;
import com.cqx.excel.model.Standard;
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
 * Created by BG307435 on 2017/8/11.
 */
@Component
public class StandardConsumer implements IExcelConsumer<Standard> {
    public static final Log log = LogFactory.getLog(BspConsumer.class);

    @Autowired
    StandardMapper bspMapper;

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

        List<Standard> bsps = new ArrayList<>();

        Sheet sheet = sheets.get(0);

        if (sheet.getLastRowNum() < 1) {
            throw new IllegalArgumentException("sheet rows must bigger than 1");
        }

        int rowNum = sheet.getLastRowNum();

        for (int i = 0; i < rowNum; i ++){
            bsps.add(fromRow(sheet.getRow(i)));
        }

        bspMapper.batchInsert(bsps);
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

    private Standard fromRow(Row row){
        Standard bsp = new Standard();
        bsp.setProvince(quickStr(row, 0));
        bsp.setCity(quickStr(row, 1));

        return bsp;
    }

    private String quickStr(Row row, int index) {
        return ExcelUtil.getCellStringValue(row, index);
    }

}
