package com.example.linebot.model.SiritoriStop;

import com.example.linebot.model.Ohters.Replier;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import java.util.List;
import java.util.Arrays;

/**
 * 計算モード終了を表示するための、LineBotの返答メッセージ用のデータを表すモデル
 */
public class SiritoriStopReplier implements Replier {
    @Override
    public List<Message> reply() {
        return Arrays.asList(new TextMessage("しりとりを終了します"));
    }
}
