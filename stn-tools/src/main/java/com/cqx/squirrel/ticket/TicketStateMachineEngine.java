package com.cqx.squirrel.ticket;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.StateMachineConfiguration;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;

public class TicketStateMachineEngine implements ApplicationContextAware {

    private UntypedStateMachineBuilder stateMachineBuilder = StateMachineBuilderFactory.create(TicketStateMachine.class, ApplicationContext.class);
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        ApplicationContext applicationContext = null;
        TicketStateMachineEngine ticketStateMachineEngine = new TicketStateMachineEngine();
        ticketStateMachineEngine.setApplicationContext(applicationContext);
        ticketStateMachineEngine.fire(TicketState.BLANK, TicketEvent.AUDIT_SUBMIT, new TicketContext());
        ticketStateMachineEngine.fire(TicketState.WAIT_AUDIT, TicketEvent.AUDIT_SUBMIT, new TicketContext());

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 每次都需要创建一个新的fsm 初始化状态 然后fire
     *
     * @param initialState
     * @param event
     * @param context
     */
    public void fire(TicketState initialState, TicketEvent event, TicketContext context) {
        UntypedStateMachine fsm = stateMachineBuilder.newUntypedStateMachine(
                initialState,
                //暂时开启debug进行日志trace
                StateMachineConfiguration.create().enableDebugMode(true).enableAutoStart(true),
                //注入applicationContext
                applicationContext);
        fsm.fire(event, context);
    }
}
