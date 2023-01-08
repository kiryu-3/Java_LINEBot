package com.example.linebot;

import com.example.linebot.model.Ohters.DialogAnswer;
import com.example.linebot.model.Ohters.Follow;
import com.example.linebot.model.Ohters.Parrot;
import com.example.linebot.model.Ohters.Replier;
import com.example.linebot.model.SiritoriAnswer.SiritoriAnswer;
import com.example.linebot.model.SiritoriStart.SiritoriStarter;
import com.example.linebot.model.SiritoriStop.SiritoriStopper;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.linecorp.bot.model.event.FollowEvent;


import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@LineMessageHandler
public class Callback {

    private static final Logger log = LoggerFactory.getLogger(Callback.class);

    private final SiritoriStarter siritoriStarter;
    private final SiritoriStopper siritoriStopper;
    private final SiritoriAnswer siritoriAnswer;

    public Callback(SiritoriStarter siritoriStarter, SiritoriStopper siritoriStopper, SiritoriAnswer siritoriAnswer) {
        this.siritoriStarter = siritoriStarter;
        this.siritoriStopper = siritoriStopper;
        this.siritoriAnswer = siritoriAnswer;
    }

    // フォローイベントに対応する
    @EventMapping
    public List<Message> handleFollow(FollowEvent event) {
        // 実際はこのタイミングでフォロワーのユーザIDをデータベースにに格納しておくなど
        Follow follow = new Follow(event);
        return follow.reply();
    }
    @EventMapping
    public List<Message> handleMessage(MessageEvent<TextMessageContent> event) {
        String message = event.getMessage().getText();

        Optional<Replier> replier = switch (message) {
            case "しりとり" -> siritoriStarter.start(event);
            case "終了" -> siritoriStopper.stop(event);
            default -> siritoriAnswer.answer(event);
        };

        return replier.orElse(new Parrot(event)).reply();
    }

    // PostBackEventに対応する
    @EventMapping
    public List<Message> handlePostBack(PostbackEvent event) {
        DialogAnswer dialogAnswer = new DialogAnswer(event);
        return dialogAnswer.reply();
    }
}