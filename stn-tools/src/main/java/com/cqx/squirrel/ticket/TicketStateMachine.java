package com.cqx.squirrel.ticket;

import org.squirrelframework.foundation.fsm.TransitionType;
import org.squirrelframework.foundation.fsm.annotation.*;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

/**
 * Finite State Machine(FSM 有限状态机)
 */
@States({
        @State(name = "WAIT_AUDIT", entryCallMethod = "entryState", exitCallMethod = "exitState")
})
@Transitions({
        @Transit(from = "BLANK", to = "WAIT_AUDIT", on = "AUDIT_SUBMIT", callMethod = "createTicket", type = TransitionType.EXTERNAL),
        @Transit(from = "WAIT_AUDIT", to = "AUDITED", on = "AUDIT_SUCCESS", callMethod = "auditSuccess"),
        @Transit(from = "WAIT_AUDIT", to = "RETURNED", on = "AUDIT_FAILED", callMethod = "auditFailed"),
        @Transit(from = "WAIT_AUDIT", to = "WAIT_AUDIT", on = "AUDIT_SUBMIT", callMethod = "auditSubmit", type = TransitionType.INTERNAL)
})
@StateMachineParameters(stateType = TicketState.class, eventType = TicketEvent.class, contextType = TicketContext.class)
public class TicketStateMachine extends AbstractUntypedStateMachine {


    public void createTicket(TicketState from, TicketState to, TicketEvent event, TicketContext context) {
        printTransition(from, to, event, context);
    }

    public void auditSuccess(TicketState from, TicketState to, TicketEvent event, TicketContext context) {
        printTransition(from, to, event, context);
    }

    public void auditFailed(TicketState from, TicketState to, TicketEvent event, TicketContext context) {
        printTransition(from, to, event, context);
    }

    public void auditSubmit(TicketState from, TicketState to, TicketEvent event, TicketContext context) {
        printTransition(from, to, event, context);
    }

    public void entryState(TicketState from, TicketState to, TicketEvent event, TicketContext context) {
        printTransition(from, to, event, context);
    }

    public void exitState(TicketState from, TicketState to, TicketEvent event, TicketContext context) {
        printTransition(from, to, event, context);
    }

    private void printTransition(TicketState from, TicketState to, TicketEvent event, TicketContext context) {
        StringBuilder sb = new StringBuilder()
                .append(from)
                .append("->")
                .append(to)
                .append(" on:")
                .append(event)
                .append(" context:")
                .append(context.toString());
        System.out.println(sb);
    }

}
