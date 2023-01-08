package com.example.linebot.model.SiritoriStop;

import com.example.linebot.model.BotInformation.BotState;
import com.example.linebot.model.Ohters.Replier;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * しりとりモードをスタートするModel（ビジネスサービスの一部）
 *
 * ユーザーIDをキーとして、ステートフルなスコープを終了する。
 */
@Service
public class SiritoriStopper {
    private final BotState botState;

    public SiritoriStopper(BotState botState) {
        this.botState = botState;
    }

    public Optional<Replier> stop(MessageEvent<TextMessageContent> event) {
        String userId = event.getSource().getUserId();
        botState.offSiritori(userId);
        return Optional.of(new SiritoriStopReplier());
    }
}
