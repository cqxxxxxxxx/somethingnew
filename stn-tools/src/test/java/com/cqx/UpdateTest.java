package com.cqx;

import com.cqx.sqlGen.ClazzContext;
import com.cqx.sqlGen.GenContext;
import com.cqx.sqlGen.db.model.User;
import com.cqx.sqlGen.excel.ExcelConsumer;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Created by BG307435 on 2017/11/13.
 */

public class UpdateTest {

    @Test
    public void getListTest() throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String path = "C:\\Users\\BG307435\\Desktop\\管理后台账号修复.xlsx";
        ExcelConsumer excelConsumer = new ExcelConsumer();
        excelConsumer.setClazzContext(new ClazzContext(User.class));
        excelConsumer.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        excelConsumer.consume(path);
        List<User> userList = excelConsumer.parseToList(excelConsumer.getSheets().get(0));
    }

    @Test
    public void testUpdate() throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException {
        GenContext genContext = GenContext.Builder.newBuilder()
                .withPath("C:\\Users\\BG307435\\Desktop\\UAT账号修复.xlsx")
                .withUpdateSQL("update system_user set username = {1}, xm = {2}, mphone = {3}, is_ldap = 1 where user_id = {4}")
                .withTargetClass(User.class)
                .build();
        String sql = genContext.updateSQL();
        File file = new File("C:\\Users\\BG307435\\Desktop\\spring30-UAT后台管理账号修复.sql");
        if (file.exists()) {
            file.delete();
        }
        OutputStream out = new FileOutputStream(file);
        out.write(sql.getBytes());
    }

}
