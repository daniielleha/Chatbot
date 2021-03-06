package com.nico.chatbot;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import Adapter.CustomAdapter;
import Helper.HttpDataHandler;
import Models.ChatModel;
import Models.QueenModels;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    List<ChatModel> list_chat = new ArrayList<>();
    FloatingActionButton btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView =(ListView) findViewById(R.id.list_of_messeg);
        editText =(EditText) findViewById(R.id.user_message);
        btn_send = (FloatingActionButton) findViewById(R.id.fab);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                ChatModel model = new ChatModel(text,true);
                list_chat.add(model);

//                String url = getResources().getString(R.string.simsimi_api) + "&lc=en&ft=1.0&text=" + text;

//                ApiCall apiCall = new ApiCall();
//                apiCall.start(getResources().getString(R.string.simsimi_api), text);

                new simsimiAPI().execute(list_chat);

                editText.setText("");
            }
        });
    }

    private class simsimiAPI extends AsyncTask<List<ChatModel>,Void,String> {
        String stream = null;
        List<ChatModel> models;
        String text = editText.getText().toString();

        protected String doInBackground (List<ChatModel>... params){
            String url = "http://sandbox.api.simsimi.com/request.p?key=" + getResources().getString(R.string.simsimi_api) + "&lc=en&ft=1.0&text=" + text +"/";
            models = params[0];
            HttpDataHandler httpDataHandler = new HttpDataHandler();
            stream = httpDataHandler.GetHTTPDATA(url);
            return stream;
        }

        protected void onPostExecute(String s){
            Gson gson = new Gson();
            QueenModels response = gson.fromJson(s, QueenModels.class);

            if (response!=null){
                ChatModel chatModel = new ChatModel(response.getResponse(),false);
                models.add(chatModel);
                CustomAdapter adapter = new CustomAdapter(models,getApplicationContext());
                listView.setAdapter(adapter);
            } else {
                Log.i("ERROR", "Error en el servidor");
            }
        }
    }
}