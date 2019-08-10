package com.cqx.stncqxhat.plugin.impl.help.core;

import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.plugin.Plugin;
import com.cqx.stncqxhat.plugin.PluginProvider;
import com.cqx.stncqxhat.support.core.ChannelContext;
import com.cqx.stncqxhat.support.keywords.KeyWordsHandler;

import java.util.Map;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/10
 */
public class HelpDefaultHandler implements KeyWordsHandler {
    private static final Map<String, Plugin> plugins;

    static {
        plugins = PluginProvider.plugins(false);
    }

    @Override
    public void handle(Message msg) {
        Message message = new Message();
        message.setFrom(msg.getFrom());
        message.setMsg(helps());
        ChannelContext.writeAndFlush(message);
    }

    private String helps() {
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
