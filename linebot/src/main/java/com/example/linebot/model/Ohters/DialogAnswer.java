package com.example.linebot.model.Ohters;

import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import java.util.Arrays;
import java.util.List;

public class DialogAnswer implements Replier {

    private PostbackEvent event;

    public DialogAnswer(PostbackEvent event) {
        this.event = event;
    }

    @Override
    public List<Message> reply() {
        String actionLabel = this.event.getPostbackContent().getData();
        switch (actionLabel) {
            case "YES":
                return Arrays.asList(new TextMessage("「しりとり」と入力してくださいなのだ"));
            case "NO":
                return Arrays.asList(new TextMessage("ぴえんなのだ"));
        }
        return Arrays.asList(new TextMessage("?"));
    }
}

