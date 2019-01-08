package com.cqx.squirrel.ticket;

public enum TicketState {

    BLANK,
    /**
     * 已经退回
     */
    RETURNED,
    /**
     * 待审核
     */
    WAIT_AUDIT,
    /**
     * 已审核
     */
    AUDITED
}
