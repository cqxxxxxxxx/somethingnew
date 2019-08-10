package com.cqx.stncqxhat.plugin.impl.help;

import com.alibaba.ttl.internal.javassist.compiler.ast.Keyword;
import com.cqx.Meta;
import com.cqx.stncqxhat.constant.ServerConst;
import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.plugin.*;
import com.cqx.stncqxhat.plugin.aop.PluginProxy;
import com.cqx.stncqxhat.plugin.impl.help.core.HelpDefaultHandler;
import com.cqx.stncqxhat.support.core.ChannelContext;
import com.cqx.stncqxhat.support.keywords.EmptyHandler;
import com.cqx.stncqxhat.support.keywords.KeyWord;
import com.cqx.stncqxhat.support.keywords.KeyWordPool;
import com.google.auto.service.AutoService;
import com.netflix.hystrix.strategy.HystrixPlugins;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.beans.BeanUtils;

import java.util.Map;
import java.util.Set;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/20
 */
@AutoService(Plugin.class)
@Meta(mode = 0, pluginName = "help plugin")
public class HelpPlugin extends AbstractPlugin {


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
                .withDefault("default", "默认行为处理器", new EmptyHandler())
                .addKeyWord("-help", "默认的处理器", new HelpDefaultHandler());
    }

    @Override
    public void act(Message m) {
        //关键字
        if (keyWordPool.isKeyWords(m.getMsg()) || keyWordPool.isKeyWords(m.getFrom().getSubKeyWord())) {

            KeyWord keyWord = keyWordPool.get(m.getMsg());
            keyWord.getHandler().handle(m);
            return;
        }
        //默认处理器
        keyWordPool.defaultKeyWord().handle(m);
    }




}
