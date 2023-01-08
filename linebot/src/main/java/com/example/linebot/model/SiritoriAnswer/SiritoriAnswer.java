package com.example.linebot.model.SiritoriAnswer;

import com.example.linebot.model.BotInformation.BotState;
import com.example.linebot.model.Ohters.Replier;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * しりとりモードの中でしりとりを行うModel（ビジネスサービスの一部）
 *
 * ユーザーIDをキーとして、入力値に対する出力値を返す。
 */
@Service
public class SiritoriAnswer {

    // ステート管理用のインスタンス
    private final BotState botState;

    public SiritoriAnswer(BotState botState) {
        this.botState = botState;
    }

    /**
     * しりとりを行い、判定結果のメッセージを作成する
     *
     * @param event LineBotから送信されたテキストメッセージイベント
     * @return 返答メッセージ用のデータ（存在しない場合は空）
     */

    public Optional<Replier> answer(MessageEvent<TextMessageContent> event) {
        String userId = event.getSource().getUserId();
        if (botState.isSiritoriMode(userId)) {
            String inputtext = event.getMessage().getText();
            String returntext =  botState.siritori(userId, inputtext);
            return Optional.of(new SiritoriAnswerReplier(returntext));
        }
        return Optional.empty();
    }
}
