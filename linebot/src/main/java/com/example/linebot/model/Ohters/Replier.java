package com.example.linebot.model.Ohters;

import com.linecorp.bot.model.message.Message;

import java.util.List;

// 返信用クラスのためのインターフェース
public interface Replier {

// 返信用クラスが必ず実装するメソッド
    List<Message> reply();

}
