package com.cqx.stncqxhat.plugin.impl.help;

import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.model.User;
import com.cqx.stncqxhat.service.UserService;
import com.cqx.stncqxhat.support.core.ChannelContext;
import com.cqx.stncqxhat.support.keywords.KeyWordsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/12
 */
@Slf4j
@Component
public class SelfHandler implements KeyWordsHandler {

    @Autowired
    UserService userService;

    @Override
    public void handle(Message message) {
        User currentUser = userService.currentUser();
        message.setMsg(currentUser.toString());
        ChannelContext.writeAndFlush(message);
    }
}
