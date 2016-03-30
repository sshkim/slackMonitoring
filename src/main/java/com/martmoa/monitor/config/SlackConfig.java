package com.martmoa.monitor.config;

/**
 * Created by Kim Seung Hwan on 2016-03-24.
 */
public class SlackConfig extends Config {

    protected String webHookUrl;
    protected String channel;
    protected int outCount;

    public SlackConfig(String webHookUrl, String channel, int outCount, boolean slackEnable, double eachCheckHour) {
        super(slackEnable, eachCheckHour, System.currentTimeMillis());

        this.webHookUrl = webHookUrl;
        this.channel = channel;
        this.outCount = outCount;
    }

    public String getWebHookUrl() {
        return webHookUrl;
    }

    public void setWebHookUrl(String webHookUrl) {
        this.webHookUrl = webHookUrl;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getOutCount() {
        return outCount;
    }

    public void setOutCount(int outCount) {
        this.outCount = outCount;
    }
}
