package com.example.linebot.model.SiritoriAnswer;

import com.example.linebot.model.SiritoriStart.TextRead;

import java.util.ArrayList;
import java.util.Random;

public class MakeList {

    ArrayList<String> siritori_list;
    ArrayList<String> OK_list = new ArrayList<>();
    Random rand = new Random();
    String inputtext;

    public MakeList(String inputtext , ArrayList<String> siritori_list){
        this.siritori_list = siritori_list;
        this.inputtext = inputtext;
        //出力できる単語のリスト（出力可リストとする）を（一応）クリアする
        this.OK_list.clear();
    }

    public String ReturnWord(){
        //入力値の最後の文字を取得する
        char initial = inputtext.charAt(inputtext.length()-1);
        //入力値の最後の文字と単語リスト内の単語の頭文字が一致した場合は、出力可リストに追加する
        for(String str : siritori_list){
            if(initial == str.charAt(0)){
                OK_list.add(str);
            }
        }

        //出力可リストが空の場合はユーザの勝利
        if(OK_list.isEmpty()){
            String noun = "USERWIN";
            return noun;
        }

        //出力可リストからランダムに値を取得する
        String noun = OK_list.get(rand.nextInt(OK_list.size()));

        return noun;
    }

}
