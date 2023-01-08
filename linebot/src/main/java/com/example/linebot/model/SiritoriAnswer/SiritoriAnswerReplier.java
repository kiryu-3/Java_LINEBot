package com.example.linebot.model.SiritoriAnswer;

import com.example.linebot.model.Ohters.Replier;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import java.util.Arrays;
import java.util.List;

public class SiritoriAnswerReplier implements Replier {

    String returntext;

    public SiritoriAnswerReplier(String returntext){
        this.returntext = returntext;
    }

    @Override
    public List<Message> reply() {
        return Arrays.asList(new TextMessage(returntext));
    }
}
