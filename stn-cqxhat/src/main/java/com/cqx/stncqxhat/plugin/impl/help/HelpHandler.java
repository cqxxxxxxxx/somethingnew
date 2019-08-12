package com.cqx.stncqxhat.plugin.impl.help;

import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.plugin.Plugin;
import com.cqx.stncqxhat.plugin.impl.EchoPlugin;
import com.cqx.stncqxhat.plugin.impl.chat.ChatPlugin;
import com.cqx.stncqxhat.support.core.ChannelContext;
import com.cqx.stncqxhat.support.keywords.KeyWordsHandler;
import com.cqx.stncqxhat.support.util.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/10
 */
@Component
@Slf4j
public class HelpHandler implements KeyWordsHandler {

//    private Map<String, Plugin> plugins;
//
//    @PostConstruct
//    public void setPlugins() {
//        plugins = ApplicationContextUtil.getBeansOfType(Plugin.class);
//    }

    @Override
    public void handle(Message msg) {
        Message message = new Message();
        message.setFrom(msg.getFrom());
        message.setMsg(helps());
        ChannelContext.writeAndFlush(message);
    }

    private String helps() {
        Map<String, Plugin> plugins = ApplicationContextUtil.getBeansOfType(Plugin.class);
        StringBuilder sb = new StringBuilder("格式<:pluginName /s>选择插件 \n");
        for (Map.Entry<String, Plugin> entry : plugins.entrySet()) {
            sb.append("插件名:")
                    .append(entry.getKey())
                    .append("   简介:")
                    .append(entry.getValue().metadata())
                    .append("\n");
        }
        return sb.toString();
    }
}
