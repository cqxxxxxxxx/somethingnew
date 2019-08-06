package com.cqx.stncqxhat.plugin.impl;

import com.cqx.Meta;
import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.plugin.AbstractPlugin;
import com.cqx.stncqxhat.plugin.Plugin;
import com.google.auto.service.AutoService;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/20
 */
@AutoService(Plugin.class)
@Meta(mode = 1, pluginName = "help plugin")
public class HelpPlugin extends AbstractPlugin {
    @Override
    public void act(Message m) {

    }

}
