package com.cqx;

import com.cqx.excel.dao.BankMapper;
import com.cqx.excel.model.Bank;
import com.cqx.excel.service.ExcelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by BG307435 on 2017/8/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelTest {

    @Autowired
    ExcelService excelService;
    @Autowired
    BankMapper bankMapper;


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

    @Test
    public void batchInsert(){

        String datas = "安徽省农村信用社\n" +
                "安阳银行\n" +
                "鞍山银行\n" +
                "包商银行\n" +
                "北京农村商业银行\n" +
                "北京银行\n" +
                "渤海银行\n" +
                "常熟农村商业银行\n" +
                "常州农村信用联社\n" +
                "朝阳银行\n" +
                "成都农商银行\n" +
                "成都银行\n" +
                "承德银行\n" +
                "城市商业银行资金清算中心\n" +
                "大连银行\n" +
                "丹东银行\n" +
                "德阳商业银行\n" +
                "德州银行\n" +
                "东莞农村商业银行\n" +
                "东莞银行\n" +
                "东亚银行\n" +
                "东营市商业银行\n" +
                "鄂尔多斯银行\n" +
                "福建海峡银行\n" +
                "抚顺银行\n" +
                "阜新银行\n" +
                "富滇银行\n" +
                "甘肃省农村信用\n" +
                "赣州银行\n" +
                "广东发展银行\n" +
                "广东南粤银行\n" +
                "广东省农村信用社联合社\n" +
                "广西北部湾银行\n" +
                "广西省农村信用\n" +
                "广州农商银行\n" +
                "广州银行\n" +
                "贵阳市商业银行\n" +
                "贵州省农村信用社\n" +
                "桂林银行\n" +
                "国家开发银行\n" +
                "邯郸银行\n" +
                "韩亚银行\n" +
                "汉口银行\n" +
                "杭州银行\n" +
                "河北省农村信用社\n" +
                "河北银行\n" +
                "河南省农村信用\n" +
                "恒丰银行\n" +
                "衡水银行\n" +
                "湖北省农村信用社\n" +
                "湖北银行\n" +
                "湖北银行黄石分行\n" +
                "湖北银行宜昌分行\n" +
                "湖南省农村信用社\n" +
                "湖州市商业银行\n" +
                "华融湘江银行\n" +
                "华夏银行\n" +
                "徽商银行\n" +
                "吉林农信\n" +
                "吉林银行\n" +
                "济宁银行\n" +
                "嘉兴银行\n" +
                "江苏江阴农村商业银行\n" +
                "江苏省农村信用联合社\n" +
                "江苏太仓农村商业银行\n" +
                "江苏银行\n" +
                "江西省农村信用\n" +
                "交通银行\n" +
                "金华银行\n" +
                "锦州银行\n" +
                "晋城银行JCBANK\n" +
                "晋商银行\n" +
                "晋中市商业银行\n" +
                "九江银行\n" +
                "开封市商业银行\n" +
                "库尔勒市商业银行\n" +
                "昆仑银行\n" +
                "昆山农村商业银行\n" +
                "莱商银行\n" +
                "兰州银行\n" +
                "廊坊银行\n" +
                "乐山市商业银行\n" +
                "辽阳市商业银行\n" +
                "临商银行\n" +
                "龙江银行\n" +
                "洛阳银行\n" +
                "南昌银行\n" +
                "南充市商业银行\n" +
                "南海农村信用联社\n" +
                "南京银行\n" +
                "内蒙古银行\n" +
                "宁波银行\n" +
                "宁夏黄河农村商业银行\n" +
                "宁夏银行\n" +
                "农信银清算中心\n" +
                "平安银行\n" +
                "平顶山银行\n" +
                "齐鲁银行\n" +
                "齐商银行\n" +
                "青岛银行\n" +
                "青海银行\n" +
                "三门峡银行\n" +
                "山东农信\n" +
                "陕西信合\n" +
                "上海农村商业银行\n" +
                "上海浦东发展银行\n" +
                "上海银行\n" +
                "上饶银行\n" +
                "绍兴银行\n" +
                "深圳农村商业银行\n" +
                "盛京银行\n" +
                "石嘴山银行\n" +
                "顺德农商银行\n" +
                "四川省农村信用\n" +
                "苏州银行\n" +
                "台州银行\n" +
                "泰安市商业银行\n" +
                "天津农商银行\n" +
                "天津银行\n" +
                "威海市商业银行\n" +
                "潍坊银行\n" +
                "温州银行\n" +
                "乌鲁木齐市商业银行\n" +
                "无锡农村商业银行\n" +
                "吴江农商银行\n" +
                "武汉农村商业银行\n" +
                "西安银行\n" +
                "新乡银行\n" +
                "信阳银行\n" +
                "邢台银行\n" +
                "兴业银行\n" +
                "许昌银行\n" +
                "阳泉银行\n" +
                "尧都农商行\n" +
                "宜宾市商业银行\n" +
                "鄞州银行\n" +
                "营口银行\n" +
                "玉溪市商业银行\n" +
                "云南省农村信用社\n" +
                "张家港农村商业银行\n" +
                "张家口市商业银行\n" +
                "长沙银行\n" +
                "招商银行\n" +
                "浙江稠州商业银行\n" +
                "浙江民泰商业银行\n" +
                "浙江省农村信用社联合社\n" +
                "浙江泰隆商业银行\n" +
                "浙商银行\n" +
                "郑州银行\n" +
                "中国工商银行\n" +
                "中国光大银行\n" +
                "中国建设银行\n" +
                "中国民生银行\n" +
                "中国农业银行\n" +
                "中国银行\n" +
                "中国邮政储蓄银行\n" +
                "中山小榄村镇银行\n" +
                "中信银行\n" +
                "重庆农村商业银行\n" +
                "重庆三峡银行\n" +
                "重庆银行\n" +
                "周口银行\n" +
                "驻马店银行\n" +
                "自贡市商业银行\n" +
                "遵义市商业银行\n";
        String[] array = datas.split("\n");
        System.out.println(array.length);

        Date date = new Date();
        for (String str : array){
            Bank bank = new Bank();
            bank.setName(str);
            bank.setStatus(0);
            bank.setCreateTime(date);
            bankMapper.insert(bank);
        }

    }
}
