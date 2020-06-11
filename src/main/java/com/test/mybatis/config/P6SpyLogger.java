package com.test.mybatis.config;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;

public class P6SpyLogger implements MessageFormattingStrategy {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    @Override
    public String formatMessage(int i, String s, long l, String s1, String s2, String s3, String s4) {
        return !"".equals(s2.trim()) ? this.format.format(new Date()) + " | took " + l + "ms | " + s1 + " | connection " + i + "\n " + s2 + ";" : "";
    }
}
