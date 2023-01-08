package com.example.linebot;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

@RestController
public class Push {

    private static final Logger log = LoggerFactory.getLogger(Push.class);

    // push先のユーザID（本来は、友達登録をした瞬間にDBなどに格納しておく）
    private String userId = "U5c9b34f90efdc2d71c6de028460112bf";

    private final LineMessagingClient client;

    @Autowired
    public Push(LineMessagingClient lineMessagingClient) {
        this.client = lineMessagingClient;
    }

    // テスト
    @GetMapping("test")
    public String hello(HttpServletRequest request) {
        return "Get from " + request.getRequestURL();
    }

    // 時報をpushする
    @GetMapping("timetone")
    public String pushTimeTone() {
        String text = DateTimeFormatter.ofPattern("a K:mm").format(LocalDateTime.now());
        try {
            PushMessage pMsg
                    = new PushMessage(userId, new TextMessage(text));
            BotApiResponse resp = client.pushMessage(pMsg).get();
            log.info("Sent messages: {}", resp);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

    @GetMapping("confirm")
    public String pushConfirm() {
        String text = "しりとり";
        try {
            Message msg = new TemplateMessage(text,
                    new ConfirmTemplate("ずんだもんとしりとりしますか？",
                            new PostbackAction("はい", "YES"),
                            new PostbackAction("いいえ", "NO")));
            PushMessage pMsg = new PushMessage(userId, msg);
            BotApiResponse resp = client.pushMessage(pMsg).get();
            log.info("Sent messages: {}", resp);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

}
