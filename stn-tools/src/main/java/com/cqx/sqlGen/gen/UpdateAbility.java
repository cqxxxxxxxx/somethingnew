package com.cqx.sqlGen.gen;

/**
 * Created by BG307435 on 2017/11/13.
 */
public interface UpdateAbility extends Gen {

    /**
     * update system_user set username = {1}, xm = {2}, mphone = {3} where user_id = {4}
     *
     * @param sql
     */
    void setUpdateSQL(String sql);
}
