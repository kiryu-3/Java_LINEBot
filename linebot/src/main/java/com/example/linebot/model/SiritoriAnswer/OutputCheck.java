package com.example.linebot.model.SiritoriAnswer;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.StringBuilder;

public class OutputCheck {
    String text;
    InputCheck ich;

    private ArrayList<String> LOWER_CHARS = new ArrayList<>(Arrays.asList("ぁ","ぃ","ぅ","ぇ","ぉ","っ","ゃ","ゅ","ょ","ゎ","ゕ","ゖ"));
    private ArrayList<String> UPPER_CHARS = new ArrayList<>(Arrays.asList("あ","い","う","え","お","つ","や","ゆ","よ","わ","か","け"));

    public OutputCheck(String text , InputCheck ich){
        this.text = text;
        this.ich = ich;
    }

    public String check(){

        StringBuilder sb = new StringBuilder();
        sb.append(text);

        //単語の末尾を取り出す
        sb.delete(0, text.length()-1);
        String matsubi = sb.toString();

        //拗音が入っている場合は大文字に変換する
        for(String youon : LOWER_CHARS){
            if(matsubi.equals(youon)){
                String newmatsubi = UPPER_CHARS.get(LOWER_CHARS.indexOf(youon));
                text = text.replaceFirst(".$", newmatsubi);
            }
        }

        //語尾が「ん」である単語をコンピュータが選んだ場合はユーザの勝利
        if(matsubi.equals("ん")){
            text  = text + " なのだ\n「ん」で終わっているなのだ\nあなたの勝ちなのだ\n「終了」と入力してください";
        }

        return text;
    }
}
