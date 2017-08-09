package com.cqx.excel.impl.export;

import com.cqx.excel.IExcelExport;
import com.cqx.excel.impl.poi.V5Poi;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by BG307435 on 2017/8/8.
 */
public class V5Export implements IExcelExport<V5Poi>{

    String[] headers = {"省份", "城市"};
    List<V5Poi> poiList = new ArrayList<>();
    private String title;

    @Override
    public String[] getHeader() {
        return headers;
    }

    @Override
    public int getHeaderSize() {
        return headers.length;
    }

    @Override
    public String getTitle() {
        if (StringUtils.isEmpty(title)) {
            return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + "-库存导出.xls";
        } else {
            return title;
        }
    }

    @Override
    public boolean containSpecialField(String filedName) {
        return false;
    }

    @Override
    public String getSpecialFieldValue(String filedName, Object value) {
        return null;
    }

    @Override
    public List<V5Poi> getPoiList() {
        return this.poiList;
    }

    @Override
    public void setPoiList(List<V5Poi> data) {
        this.poiList = data;
    }
}
