package com.cqx.sql_gen.consumer;

import com.cqx.sql_gen.AbstractConsumer;
import com.cqx.sql_gen.InsertAbility;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by BG307435 on 2017/9/18.
 */
public class GenInsert extends AbstractConsumer implements InsertAbility {

    private List<String> columns = new ArrayList<>(50);
    private String table;
    private static final String PREFIX = "(";
    private static final String SUFFIX = ")";
    private static final String COMMA = ",";
    private static final String QUOTES = "\'";
    private StringBuilder sb = new StringBuilder();

    @Override
    public void setInsertColumns(String columns) {
        String[] strs = columns.split(",");
        this.columns = Arrays.asList(strs);
    }

    @Override
    public void setTableName(String tableName) {
        this.table = tableName;
    }

    /**
     * INSERT INTO `sub_bank` (`id`, `flow_num`, `name`) VALUES
     * (490376, 'BANK00010283', '安徽长丰农村商业银行股份有限公司'),
     * (490377, 'BANK00010284', '安徽长丰农村商业银行股份有限公司埠里支行')
     */
    @Override
    public String gen() {
        sb.append("INSERT INTO ")
                .append(table);
        genColumn();
        sb.append(" VALUES ");
        dealSheet(sheets.get(0));
        return sb.toString();
    }

    private void dealSheet(Sheet sheet) {
        if (sheet.getLastRowNum() < 1) {
            throw new IllegalArgumentException("sheet rows must bigger than 1");
        }
        Row row;
        Cell cell;
        final int rowNum = sheet.getLastRowNum();
        final int columnSize = columns.size();

        for (int i = 0; i < rowNum; i++) {
            row = sheet.getRow(i);
            addPrefix();
            for (int j = 0; j < columnSize; j++) {
                cell = row.getCell(j);
                System.out.println(cell == null ? "" : cell.getCellType());
                if (cell == null) {
                    sb.append("NULL");
                } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && HSSFDateUtil.isCellDateFormatted(cell)) {
                    wrap(getDateValue(cell));
                } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    sb.append(getIntValue(cell));
                } else {
                    wrap(getStringValue(cell));
                }
                addComma();
            }
            addSuffix();
            addComma();
        }

        if (sb.lastIndexOf(COMMA) == sb.length() - 1) {
            //删除逗号
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private void genColumn() {
        addPrefix();
        columns.stream().forEach(x -> {
            wrap(x);
            addComma();
        });
        addSuffix();
    }

    private void addComma() {
        sb.append(COMMA);
    }

    private void wrap(String str) {
        if (StringUtils.isEmpty(str)) {
            sb.append("NULL");
        } else {
            sb.append(QUOTES)
                    .append(str)
                    .append(QUOTES);
        }
    }

    private void addPrefix() {
        sb.append(PREFIX);
    }

    private void addSuffix() {
        if (sb.lastIndexOf(COMMA) == sb.length() - 1) {
            //删除逗号
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(SUFFIX);
    }
}
