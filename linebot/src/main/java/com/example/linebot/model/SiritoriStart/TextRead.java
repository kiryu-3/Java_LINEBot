package com.example.linebot.model.SiritoriStart;

//import FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextRead {

    ArrayList<String> read_list = new ArrayList<>();

    public TextRead() {}

    public ArrayList<String> textRead(){
        try {
            File file = new File("C:\\Users\\kaoru\\Documents\\linebot\\linebot\\src\\main\\java\\com\\example\\linebot\\model\\SiritoriStart\\output.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String str;
            while ((str = br.readLine()) != null) {
                // テキストファイルの一行を空白で区切る
                read_list.add(str);
                // 項目とそのパスワードをArrayListに追加
            }
            br.close();
        } catch (IOException e) {
            System.out.println("ファイルが存在しません");
        }
        return read_list;
    }
}
