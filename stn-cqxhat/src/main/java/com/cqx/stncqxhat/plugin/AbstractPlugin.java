package com.cqx.stncqxhat.plugin;

import com.cqx.stncqxhat.constant.ServerConst;
import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.model.User;
import com.cqx.stncqxhat.service.UserService;
import com.cqx.stncqxhat.support.core.ChannelContext;
import com.cqx.stncqxhat.support.keywords.KeyWord;
import com.cqx.stncqxhat.support.keywords.KeyWordPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
@Slf4j
public abstract class AbstractPlugin implements Plugin {

    protected KeyWordPool keyWordPool;
    @Autowired
    UserService userService;

    @PostConstruct
    @Override
    public void initialize() {
        keyWordPool = KeyWordPool.of(this);
    }

    @Override
    public void act(Message m) {
        User currentUser = userService.currentUser();
        boolean fitted = ifOnKeyWord(currentUser, m)
                || ifInKeyWord(currentUser, m)
                || elseDefault(currentUser, m);
        if (!fitted) {
            log.error("处理异常 message:{}; user:{}", m, currentUser);
        }
    }

    public int mode() {
        return this.metadata().getMode();
    }

    public int shiftL(int n) {
        return 1 << n;
    }


    /**
     * 匹配到了关键字,执行功能切换
     * @param user
     * @param msg
     * @return
     */
    protected boolean ifOnKeyWord(User user, Message msg) {
        if (keyWordPool.isKeyWords(msg.getMsg())) {
            user.setSubKeyWord(msg.getMsg());
            userService.update(user);
            ChannelContext.writeAndFlush(Message.of(ServerConst.SYSTEM_USER, "switch success, you are now in " + msg));
            return true;
        }
        return false;
    }

    /**
     * 在关键字的功能中,执行关键字的功能
     * @param user
     * @param message
     * @return
     */
    protected boolean ifInKeyWord(User user, Message message) {
        if (keyWordPool.isKeyWords(user.getSubKeyWord())) {
            KeyWord keyWord = keyWordPool.get(user.getSubKeyWord());
            keyWord.getHandler().handle(message);
            return true;
        }
        return false;
    }

    /**
     * 默认处理
     * @param user
     * @param message
     * @return
     */
    protected boolean elseDefault(User user, Message message) {
        //默认处理器
        message.setMsg(keyWordPool.getPool()
                .values()
                .stream()
                .map(x -> {
                    return x.getKey() + "  介绍:" + x.getInfo();
                })
                .collect(Collectors.joining("\n")).trim());
        keyWordPool.defaultKeyWord().handle(message);
        return true;
    }
}
