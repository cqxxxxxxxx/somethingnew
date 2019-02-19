package com.cqx.stncqxhat.plugin;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
public class PluginUtil {
    private static final String PREFIX = ":";
    private static final Pattern PLUGIN_NAME = Pattern.compile("(:\\b\\w+\\b)");

    public static Plugin getPlugin(int mode) {
        return PluginProvider.plugins(false).entrySet().stream()
                .filter(x -> mode == x.getValue().metadata().getMode())
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);
    }

    public static String getPluginName(String body) {
        Matcher matcher = PLUGIN_NAME.matcher(body);
        matcher.find();
        String name = matcher.group();
        return name.substring(1);
    }

    public static int getMode(String pluginName) {
        return getMode(PluginProvider.find(pluginName));
    }

    public static int getMode(Plugin plugin) {
        return plugin.metadata().getMode();
    }

    public static void main(String[] args) {
        System.out.println(getPluginName(":my world"));
    }


}
