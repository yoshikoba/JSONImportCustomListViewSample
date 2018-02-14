package com.example.kazukoba.jsonimportcustomlistviewsample;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    List<ListData> dataList = new ArrayList<ListData>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager assetManager = getApplicationContext().getResources().getAssets();
        try {
            //ファイルの読み込み
            InputStream inputStream = assetManager.open("shop.json");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder strbuilder = new StringBuilder();

            //読み込んだ内容をStringに変換
            String inputStr;
            while ((inputStr = bufferedReader.readLine()) !=null) {
                strbuilder.append(inputStr);
            }
            inputStream.close();
            bufferedReader.close();

            ArrayList<ListData> list = new ArrayList<>();
            //JSONオブジェクトに変換
            JSONObject jsonObject = new JSONObject(strbuilder.toString());
            JSONArray datas = jsonObject.getJSONArray("data");
            for (int i = 0; i < datas.length(); i++) {
                JSONObject data = datas.getJSONObject(i);
                // 名前を取得
                String shopName = data.getString("shopName");
                String areaName = data.getString("area");

                ListData listData = new ListData();
                listData.setShopName(shopName);
                listData.setArea(areaName);
                list.add(listData);
//                Log.d("data",shopName);
//                Log.d("data",areaName);

            }

            listView = findViewById(R.id.listView);

            ShopListAdapter adapter = new ShopListAdapter(MainActivity.this);
            adapter.setShopList(list);
            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
