package com.ss.lab.sprdemo.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取配置文件值的工具类
 * <p>
 * Created by yong on 2018/2/23.
 */
public class PropertyHolder extends PropertyPlaceholderConfigurer {
    private static Map ctxPropertiesMap = new HashMap();

    @Override
    protected void processProperties(
            ConfigurableListableBeanFactory beanFactoryToProcess,
            Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }

    public static Object getContextProperty(String name) {
        return ctxPropertiesMap.get(name);
    }

    public static String getProperty(String name, String defaultVal) {
        if (ctxPropertiesMap.containsKey(name)) {
            return ctxPropertiesMap.get(name) + "";
        } else {
            return defaultVal;
        }
    }
}
