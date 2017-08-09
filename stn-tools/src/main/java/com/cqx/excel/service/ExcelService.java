package com.cqx.excel.service;

import com.cqx.excel.ExcelUtil;
import com.cqx.excel.IExcelExport;
import com.cqx.excel.dao.V5AddrMapper;
import com.cqx.excel.dao.WaveMapper;
import com.cqx.excel.impl.consumer.V5AddrConsumer;
import com.cqx.excel.impl.consumer.WaveConsumer;
import com.cqx.excel.impl.export.V5Export;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by BG307435 on 2017/8/8.
 */
@Service
public class ExcelService {

    public static final Log log = LogFactory.getLog(ExcelService.class);
    private static final String V5_PATH = "C:\\Users\\BG307435\\Desktop\\v5.xlsx";
    private static final String WAVE_PATH = "C:\\Users\\BG307435\\Desktop\\wave.xlsx";


    @Autowired
    V5AddrConsumer v5AddrConsumer;
    @Autowired
    WaveConsumer waveConsumer;

    public void importToV5(){
        try {
            ExcelUtil.excelImport(new File(V5_PATH), "v5.xlsx", v5AddrConsumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void importToWave(){
        try {
            ExcelUtil.excelImport(new File(WAVE_PATH), "wave.xlsx", waveConsumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportV5() throws FileNotFoundException {

        FileOutputStream out = new FileOutputStream("C:\\Users\\BG307435\\Desktop\\v5export.xlsx");
        IExcelExport excelExport = new V5Export();
        excelExport.setPoiList(new ArrayList());
        ExcelUtil.export(excelExport, out);
    }
}
