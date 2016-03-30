package com.martmoa.monitor.appender;

import com.martmoa.monitor.config.SlackConfig;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackField;
import net.gpedro.integrations.slack.SlackMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Kim Seung Hwan on 2016-03-24.
 */
public class SlackAppender extends SlackConfig implements Appender {

    private String className;
    private int count = 0;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    List<SlackField> fields = new ArrayList<SlackField>();

    public SlackAppender(Class className, String webHookUrl, String channel, int outCount, boolean slackEnable, double eachCheckHour) {
        super(webHookUrl, channel, outCount, slackEnable, eachCheckHour);

        this.className = className.toString();
    }

    public boolean inspector() {
        count++;
        if (outCount <= count) {
            lastCheckTime = System.currentTimeMillis();
            return true;
        }
        if (System.currentTimeMillis() - lastCheckTime >= eachCheckHour) {
            lastCheckTime = System.currentTimeMillis();
            fields.clear();
            count = 0;
        }
        return false;
    }

    public void addMessage(String key, String value) {
        SlackField message = new SlackField();
        message.setTitle(key);
        message.setValue(value);
        message.setShorten(false);
        fields.add(message);
    }


    public void toSlack(String errorMsg) {
        if (!isEnabled()) return;

        addMessage("에러내용", errorMsg);
        addMessage("발생시간", dateFormat.format(new Date()));

        if (!inspector()) return;
        msgToSlack();
    }


    public void toSlack(String errorMsg, String contents) {
        if (!isEnabled()) return;

        addMessage("에러내용", errorMsg);
        addMessage("추가정보", contents);
        addMessage("발생시간", dateFormat.format(new Date()));

        if (!inspector()) return;
        msgToSlack();
    }

    private void msgToSlack() {
        SlackApi slackApi = new SlackApi(webHookUrl);

        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setFallback("Error Message");
        slackAttachment.setColor("danger");
        slackAttachment.setFields(fields);
        slackAttachment.setTitle(className);

        SlackMessage slackMessage = new SlackMessage("");
        slackMessage.setChannel("#" + channel);
        slackMessage.setIcon(":exclamation:");
        slackMessage.setAttachments(Collections.singletonList(slackAttachment));

        slackApi.call(slackMessage);

        lastCheckTime = System.currentTimeMillis();
        fields.clear();
        count = 0;
    }

}
