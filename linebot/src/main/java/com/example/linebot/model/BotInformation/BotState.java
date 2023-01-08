package com.example.linebot.model.BotInformation;

import com.example.linebot.model.BotInformation.BotMode;
import com.example.linebot.model.SiritoriAnswer.InputCheck;
import com.example.linebot.model.SiritoriAnswer.MakeList;
import com.example.linebot.model.SiritoriAnswer.OutputCheck;
import com.example.linebot.model.SiritoriStart.TextRead;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/**
 * ユーザーIDをキーとして、ユーザーIDごとに記録するステートフルな情報を管理するデータ管理用のモデル。
*/
@Repository
public class BotState {
    private final Map<String, BotMode> userState;
    private final Map<String, ArrayList<String>> all_list;
    private final Map<String, ArrayList<String>> game_list;
    private final Map<String, String> lastword;

    ArrayList<String> game_siritori_list;

    public BotState() {

        // ユーザーIDごとのモードを保持
        this.userState = new HashMap<>();
        // ユーザーIDごとのしりとりの単語リストを保持
        this.all_list = new HashMap<>();
        // ユーザーIDごとのしりとりの使用済みリストを保持
        this.game_list = new HashMap<>();
        // ユーザーIDごとのしりとりの前の単語を保持
        this.lastword = new HashMap<>();
    }

    /**
     * しりとりモードをON（セッションスコープの開始）
     *
     * @param userId キーとなるユーザーID
     */
    public void onSiritori(String userId) {
        //しりとりモードスタート
        userState.put(userId, BotMode.Siritori);

        //単語データを単語リストに読み込み
        TextRead txr = new TextRead();
        ArrayList<String> siritori_list = txr.textRead();
        all_list.put(userId, siritori_list);

        //最初は「しりとり」からスタートしているので使用済みリストに追加する
        game_siritori_list = new ArrayList<>();
        game_siritori_list.add("しりとり");
        game_list.put(userId, game_siritori_list);

        //最初は「しりとり」からスタートなので前の単語も「しりとり」
        lastword.put(userId,"しりとり");
    }

    public ArrayList<String> getGame_siritori_list() {
        return game_siritori_list;
    }

    /**
     * しりとりモードをOFF（セッションスコープを破棄）
     *
     * @param userId キーとなるユーザーID
     */
    public void offSiritori(String userId) {
        userState.remove(userId);
        all_list.remove(userId);
        game_list.remove(userId);
        lastword.remove(userId);
    }

    /**
     * しりとりモードかどうかを調べる
     *
     * @param userId キーとなるユーザーID
     * @return しりとりモードであれば true, それ以外は false;
     */
    public boolean isSiritoriMode(String userId) {
        BotMode mode = userState.get(userId);
        return mode != null && mode.equals(BotMode.Siritori);
    }

    /**
     * 入力値から次の語句を探し、その結果を返す
     *
     * @param userId キーとなるユーザーID
     * @param inputtext ユーザーが入力した値
     * @return 最新の出力値
     */
    public String siritori(String userId, String inputtext) {
        //記憶しているデータから値を取り出す
        ArrayList<String> siritori_list = all_list.get(userId);
        ArrayList<String> game_siritori_list = game_list.get(userId);
        String old_noun = lastword.get(userId);
        String text;

        //ユーザの入力の不備チェック
        InputCheck ich = new InputCheck(inputtext , game_siritori_list , old_noun);
        String flag = ich.check();

        if(!(flag.equals("NOTUSERLOSE"))){
            //不備があった場合は不備情報を返ししりとり終了
            return flag;
        }else{
            //不備がなければ入力値を使用済みリストに追加する
            game_siritori_list.add(flag);
            game_list.put(userId, game_siritori_list);
            //不備がなければ入力値を単語リストから除外する
            siritori_list.remove(flag);
            all_list.put(userId,siritori_list);

            //次の単語をコンピュータが探す
            MakeList mkl = new MakeList(ich.getInputtext() , siritori_list);
            flag = mkl.ReturnWord();

            if(flag.equals("USERWIN")){
                //単語リストから見つけられなかった場合はしりとり終了
                return "返す語句がありませんなのだ\nあなたの勝ちなのだ\n「終了」と入力してくださいなのだ";
            }else{
                //コンピュータが選んだ単語の不備を確認する
                OutputCheck och = new OutputCheck(flag , ich);
                text = och.check();

                //出力値を使用済みリストに追加する
                game_siritori_list.add(text);
                game_list.put(userId, game_siritori_list);
                //単語を修正し、前の単語として追加する
                lastword.put(userId, text);
                //修正した出力値を単語リストから除外する
                siritori_list.remove(text);
                all_list.put(userId,siritori_list);
            }
            //勝敗がつかない場合は、コンピュータが選んだ値を返す
            //コンピュータが最後に「ん」がつく言葉を選んだ場合もこちらで返す
            return text + " なのだ";
        }
    }
}
