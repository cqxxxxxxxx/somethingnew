package com.cqx.stncqxhat.plugin.impl.help;

import com.cqx.Meta;
import com.cqx.stncqxhat.constant.ServerConst;
import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.model.User;
import com.cqx.stncqxhat.plugin.*;
import com.cqx.stncqxhat.service.UserService;
import com.cqx.stncqxhat.support.core.ChannelContext;
import com.cqx.stncqxhat.support.keywords.EmptyHandler;
import com.cqx.stncqxhat.support.keywords.KeyWord;
import com.cqx.stncqxhat.support.keywords.PrintHandler;
import com.cqx.stncqxhat.support.util.ApplicationContextUtil;
import com.google.auto.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/20
 */
@Component
@AutoService(Plugin.class)
@Meta(mode = 0, pluginName = "help plugin")
public class HelpPlugin extends AbstractPlugin {

    @Autowired
    private SelfHandler selfHandler;
    @Autowired
    private HelpHandler helpHandler;
//    /**
//     * lazy-load singleton
//     */
//    private static class LazyHolder { private static final HelpPlugin INSTANCE = new HelpPlugin(); }
//
//    public static HelpPlugin getInstance() {
//        return LazyHolder.INSTANCE;
//    }


    @Override
    public void initialize() {
        super.initialize();
        keyWordPool
                .withDefault("default", "默认行为处理器", PrintHandler.getInstance())
                .addKeyWord("-help", "默认的处理器", helpHandler)
                .addKeyWord("-self", "默认的处理器", selfHandler);

    }
}
