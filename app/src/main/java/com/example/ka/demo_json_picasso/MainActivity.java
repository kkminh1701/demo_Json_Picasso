package com.example.ka.demo_json_picasso;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list_view_CaSi;
    ArrayList<CaSi> arrayList;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_view_CaSi = findViewById(R.id.list_view_CaSi);
//        imageView2 = findViewById(R.id.imageView2);
//        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(imageView2);

        arrayList = new ArrayList<CaSi>();
//        arrayList.add(new CaSi("Minh", "minh@gmail.com",1997, "http://i.imgur.com/DvpvklR.png"));
//        arrayList.add(new CaSi("Minh", "minh@gmail.com",1997, "http://i.imgur.com/DvpvklR.png"));

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJson().execute("https://khoapham.vn/KhoaPhamTraining/android/json/demo7.php");
            }
        });
//        ListAdapter adapter = new ListAdapter(this, R.layout.item_list,arrayList);
//        list_view_CaSi.setAdapter(adapter);
    }

    class ReadJson extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray jsonArray = new JSONArray(s);
                for(int i = 0; i < jsonArray.length(); i ++)
                {
                    JSONObject casi = jsonArray.getJSONObject(i);
                    arrayList.add(new CaSi(
                            casi.getString("hoten"),
                            casi.getString("email"),
                            casi.getInt("namsinh"),
                            casi.getString("hinh")));
                }
                ListAdapter adapter = new ListAdapter(getApplicationContext(), R.layout.item_list,arrayList);
                list_view_CaSi.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
