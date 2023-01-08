package com.example.linebot.model.Ohters;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import java.util.Arrays;
import java.util.List;

// おうむ返し用の返信クラス
public class Parrot implements Replier {

    private MessageEvent<TextMessageContent> event;

    public Parrot(MessageEvent<TextMessageContent> event) {
        this.event = event;
    }

    @Override
    public List<Message> reply() {
        TextMessageContent tmc = this.event.getMessage();
        String text = tmc.getText();
        return Arrays.asList(new TextMessage(text));
    }

}