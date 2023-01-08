package com.example.linebot.model.SiritoriStart;

import com.example.linebot.model.BotInformation.BotState;
import com.example.linebot.model.Ohters.Replier;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import java.util.Arrays;
import java.util.List;

/**
 * しりとりモード開始を表示するための、LineBotの返答メッセージ用のデータを表すモデル
 */
public class SiritoriStartReplier implements Replier {
    @Override
    public List<Message> reply() {
        BotState bot = new BotState();
        return Arrays.asList(new TextMessage("しりとりを開始しますなのだ\nひらがなしか入力できませんなのだ\n「終了」と入力すると終了しますなのだ"), new TextMessage("しりとり なのだ"));
    }
}
