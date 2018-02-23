package com.ss.lab.sprdemo.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by yong on 2018/2/23.
 */
public class LogTest {

    private static Logger logger = LogManager.getLogger(LogTest.class);


    @Test
    public void testLog(){
        logger.debug("debug ...");
        logger.info("info ---");
        logger.warn("warn +++");
        logger.error("error ===");
    }
}
