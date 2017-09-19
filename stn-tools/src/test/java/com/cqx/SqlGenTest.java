package com.cqx;

import com.cqx.sql_gen.consumer.GenInsert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BG307435 on 2017/9/18.
 */
public class SqlGenTest {
    private static final String BSP_PATH = "C:\\Users\\BG307435\\Desktop\\test.xlsx";
    @Test
    public void sqlGenTest() throws IOException {
        GenInsert genInsert = new GenInsert();
        File file = new File(BSP_PATH);
        genInsert.consumer(file, "test.xlsx");
        genInsert.setTableName("carrier");
        genInsert.setInsertColumns("id,num,name,time_time");
        String sql = genInsert.gen();
        System.out.println(sql);
    }

    @Test
    public void dateTest(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(dateFormat.format(date));
    }
}
