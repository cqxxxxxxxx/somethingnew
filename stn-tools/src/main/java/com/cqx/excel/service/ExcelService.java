package com.cqx.excel.service;

import com.cqx.excel.ExcelUtil;
import com.cqx.excel.IExcelExport;
import com.cqx.excel.dao.V5AddrMapper;
import com.cqx.excel.dao.WaveMapper;
import com.cqx.excel.impl.consumer.BspConsumer;
import com.cqx.excel.impl.consumer.StandardConsumer;
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
    private static final String BSP_PATH = "C:\\Users\\BG307435\\Desktop\\bsp.xlsx";
    private static final String STANDARD_PATH = "C:\\Users\\BG307435\\Desktop\\standard.xlsx";



    @Autowired
    V5AddrConsumer v5AddrConsumer;
    @Autowired
    WaveConsumer waveConsumer;
    @Autowired
    BspConsumer bspConsumer;
    @Autowired
    StandardConsumer standardConsumer;


    public void importToBsp(){
        try {
            ExcelUtil.excelImport(new File(BSP_PATH), "bsp.xlsx", bspConsumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void importToStandard(){
        try {
            ExcelUtil.excelImport(new File(STANDARD_PATH), "standard.xlsx", standardConsumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void importToV5(){
//        try {
//            ExcelUtil.excelImport(new File(V5_PATH), "v5.xlsx", v5AddrConsumer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void importToWave(){
//        try {
//            ExcelUtil.excelImport(new File(WAVE_PATH), "wave.xlsx", waveConsumer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void exportV5() throws FileNotFoundException {

        FileOutputStream out = new FileOutputStream("C:\\Users\\BG307435\\Desktop\\v5export.xlsx");
        IExcelExport excelExport = new V5Export();
        excelExport.setPoiList(new ArrayList());
        ExcelUtil.export(excelExport, out);
    }
}
