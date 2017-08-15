package com.cqx.excel.impl.consumer;

import com.cqx.excel.ExcelUtil;
import com.cqx.excel.IExcelConsumer;
import com.cqx.excel.dao.BspMapper;
import com.cqx.excel.dao.V5AddrMapper;
import com.cqx.excel.model.Bsp;
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
 * Created by BG307435 on 2017/8/11.
 */
@Component
public class BspConsumer implements IExcelConsumer<Bsp> {
    public static final Log log = LogFactory.getLog(BspConsumer.class);

    @Autowired
    BspMapper bspMapper;

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

        List<Bsp> bsps = new ArrayList<>();

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

    private Bsp fromRow(Row row){
        Bsp bsp = new Bsp();
        bsp.setCityId(ExcelUtil.getCellIntValue(row.getCell(0), 0));
        bsp.setProvince(quickStr(row, 1));
        bsp.setName(quickStr(row, 3));

        return bsp;
    }

    private String quickStr(Row row, int index) {
        return ExcelUtil.getCellStringValue(row, index);
    }

}
