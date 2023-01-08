package com.example.linebot.model.SiritoriStart;

import com.example.linebot.model.BotInformation.BotState;
import com.example.linebot.model.Ohters.Replier;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * しりとりモードをスタートするModel（ビジネスサービスの一部）
 *
 * ユーザーIDをキーとして、ステートフルなスコープを開始する。
 */
@Service
public class SiritoriStarter {

    private final BotState botState;


    public SiritoriStarter(BotState botState) {
        this.botState = botState;
    }

    /**
     * しりとりモードを開始し、通知用のメッセージを作成する
     *
     * @param event LineBotから送信されたテキストメッセージイベント
     * @return 返答メッセージ用のデータ（存在しない場合は空）
     */

    public Optional<Replier> start(MessageEvent<TextMessageContent> event) {
        String userId = event.getSource().getUserId();
        botState.onSiritori(userId);
        return Optional.of(new SiritoriStartReplier());
    }
}
