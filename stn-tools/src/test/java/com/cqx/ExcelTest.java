package com.cqx;

import com.cqx.excel.service.ExcelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by BG307435 on 2017/8/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelTest {

    @Autowired
    ExcelService excelService;


//    @Test
//    public void v5(){
//        excelService.importToV5();
//    }
//
//    @Test
//    public void wave(){
//        excelService.importToWave();
//    }


    @Test
    public void bsp(){
        excelService.importToBsp();
    }

    @Test
    public void standard(){
        excelService.importToStandard();
    }
}
