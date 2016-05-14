package com.example.developer.agripepper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * Created by developer on 2016/04/09.
 */
public class ButtonActivity extends Activity {

    @Override
    public void onCreate(Bundle saveInstance){
        super .onCreate(saveInstance);
        setContentView(R.layout.search);

        Button btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // 画面で入力された値のチェック

                // 入力された値をサーバーへ渡す

                // 取得した検索結果が存在する場合

                // サーバーから受け取った値を画面表示表に格納

                // ※ とりあえずサーバーの返却値をダミーで作成してみる

                // 取得結果が存在しない場合

                // 「取得結果が0件」である旨のエラーメッセージを設定

                // 処理結果を画面に返却する

            }
        });
    }

}
