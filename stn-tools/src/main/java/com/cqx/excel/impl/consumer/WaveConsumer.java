package com.cqx.excel.impl.consumer;

import com.cqx.excel.ExcelUtil;
import com.cqx.excel.IExcelConsumer;
import com.cqx.excel.dao.V5AddrMapper;
import com.cqx.excel.dao.WaveMapper;
import com.cqx.excel.model.V5Addr;
import com.cqx.excel.model.Wave;
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
 * Created by BG307435 on 2017/8/8.
 */
@Component
public class WaveConsumer implements IExcelConsumer<Wave> {

    public static final Log log = LogFactory.getLog(WaveConsumer.class);

    @Autowired
    WaveMapper waveMapper;

    @Override
    public void importExcel(Workbook workbook) {
        List<Sheet> sheets = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        sheets.add(sheet); //sheet 1
        consume(sheets);
    }

    @Override
    public void consume(List<Sheet> sheets) {
        log.info("starting consume task...");

        List<Wave> waves = new ArrayList<>();

        Sheet sheet = sheets.get(0);

        if (sheet.getLastRowNum() < 1) {
            throw new IllegalArgumentException("sheet rows must bigger than 1");
        }

        int rowNum = sheet.getLastRowNum();

        for (int i = 0; i < rowNum; i ++){
            waves.add(fromRow(sheet.getRow(i)));
        }

        waveMapper.batchInsert(waves);
        log.info("end consume task...");
    }

    @Override
    public void consume(Sheet sheet) {

    }

    @Override
    public boolean multi() {
        return false;
    }

    private Wave fromRow(Row row){
        Wave wave = new Wave();
        wave.setProvince(quickStr(row, 0));
        wave.setCity(quickStr(row, 1));
        return wave;
    }

    private String quickStr(Row row, int index) {
        return ExcelUtil.getCellStringValue(row, index);
    }
}
