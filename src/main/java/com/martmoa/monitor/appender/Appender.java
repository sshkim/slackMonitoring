package com.martmoa.monitor.appender;

/**
 * Created by Kim Seung Hwan on 2016-03-24.
 */
public interface Appender {

    boolean inspector();
    void addMessage(String key, String value);

}
