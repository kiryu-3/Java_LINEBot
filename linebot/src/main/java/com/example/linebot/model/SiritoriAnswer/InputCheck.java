package com.example.linebot.model.SiritoriAnswer;

import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Arrays;

public class InputCheck {

    StringBuilder sb;
    ArrayList<String> game_siritori_list;
    String inputtext;
    String old_noun;
    String matsubi;

    private ArrayList<String> LOWER_CHARS = new ArrayList<>(Arrays.asList("ぁ","ぃ","ぅ","ぇ","ぉ","っ","ゃ","ゅ","ょ","ゎ","ゕ","ゖ"));
    private ArrayList<String> UPPER_CHARS = new ArrayList<>(Arrays.asList("あ","い","う","え","お","つ","や","ゆ","よ","わ","か","け"));

    public InputCheck(String inputtext , ArrayList<String> game_siritori_list , String old_noun){
        this.inputtext = inputtext;
        this.game_siritori_list = game_siritori_list;
        this.old_noun = old_noun;
    }

    public String check(){
        //使用済みリスト内に入力値が存在した場合はしりとり終了
        for(String str : game_siritori_list){
            if(str.equals(inputtext)){
                return inputtext + "はすでに使われています。\nあなたの負けです。\n「終了」と入力してください";
            }
        }

        sb = new StringBuilder();
        sb.append(inputtext);
        //入力値の末尾を取り出す
        sb.delete(0, inputtext.length()-1);
        matsubi = sb.toString();

        //入力値の末尾が「ー」の場合は抜く
        if(matsubi.equals("ー")){
            sb = new StringBuilder();
            sb.append(inputtext);

            sb.setLength(sb.length()-1);
            inputtext = sb.toString();
        }

        //拗音が入っている場合は大文字に変換する
        for(String youon : LOWER_CHARS){
            if(matsubi.equals(youon)){
                String newmatsubi = UPPER_CHARS.get(LOWER_CHARS.indexOf(youon));
                inputtext = inputtext.replaceFirst(".$", newmatsubi);
            }
        }

        //前の単語の語尾から始まる単語が入力されたか確認
        if(old_noun.charAt(old_noun.length()-1) != inputtext.charAt(0)){
            sb = new StringBuilder();
            sb.append(old_noun);

            //前の単語の末尾を取り出す
            sb.delete(0, old_noun.length()-1);
            String oldmatsubi = sb.toString();

            //始まっていなかったらしりとり終了
            return oldmatsubi + " から始まっていませんなのだ\nあなたの負けなのだ\n「終了」と入力してくださいなのだ";
        }

        //入力値が「ん」で終わっていないかどうか確認
        if(matsubi.equals("ん")){
            //「ん」で終わっていたらしりとり終了
            return "「ん」で終わっていますなのだ\nあなたの負けなのだ\n「終了」と入力してくださいなのだ";
        }

        //諸々のif文に引っかからなかった場合は、"負け"判定を出せなかったことを返す
        return "NOTUSERLOSE";
    }

    public String getInputtext() {
        return inputtext;
    }
}
