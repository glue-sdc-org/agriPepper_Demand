package com.example.developer.agripepper;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 *
 */

public class HttpResponsAsync extends AsyncTask<String, Void, String> {

    // Activity オブジェクト生成
    private Activity mainActivity;

    public HttpResponsAsync(Activity activity) {
        // 呼び出し元のアクティビティ
        this.mainActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // doInBackground前処理
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            // (2)URLクラスを使用して通信を行う
            URL url = new URL(params[0]);
            URLConnection connection = url.openConnection();
            // 動作を入力に設定
            connection.setDoInput(true);
            InputStream stream = connection.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(stream));

            // (3)データの取得
            String data = "";
            String tmp = "";
            while ((tmp = input.readLine()) != null) {
                data += tmp;
            }
            // (4)終了処理
            stream.close();
            input.close();
            return data;
        } catch (Exception e) {
            // (5)エラー処理
            e.printStackTrace();
            return e.toString();
        }
    }

    protected void onPostExecute(String result) {

        TextView tv = (TextView) mainActivity.findViewById(R.id.action_settings);
        JsonFactory factory = new JsonFactory();
        try {
            JsonParser parser = factory.createParser(result);
            ArrayList<String> title = new ArrayList<String>();
            int i = 0;
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    String name = parser.getCurrentName();
                    if (name != null) {
                        parser.nextToken();
                        Log.d("name", name);
                        if (name.equals("title")) {
                            title.add(parser.getText());
                        }
                    }
                }
            }
            String titles = "";
            for (i = 0; i < title.size(); i++) {
                titles = titles + "title:" + title.get(i) + "\n";
            }
            tv.setText(titles);
        } catch (JsonParseException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        //tv.setText(result);
    }
}
