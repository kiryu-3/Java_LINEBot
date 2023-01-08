package com.example.linebot.model.Ohters;

import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import java.util.Arrays;
import java.util.List;

// フォローされた時用の返信クラス
public class Follow implements Replier {

    private FollowEvent event;

    public Follow(FollowEvent event) {
        this.event = event;
    }

    @Override
    public List<Message> reply() {
        String userId = this.event.getSource().getUserId();
        String text = String.format("あなたのユーザーID:%s", userId);
        return Arrays.asList(new TextMessage(text));
    }

}