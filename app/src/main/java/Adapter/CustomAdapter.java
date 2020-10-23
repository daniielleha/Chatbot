package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.github.library.bubbleview.BubbleTextView;
import com.nico.chatbot.R;

import java.util.ConcurrentModificationException;
import java.util.List;

import Models.ChatModel;

public class CustomAdapter extends BaseAdapter {

    private List<ChatModel> list_chat_models;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapter (List <ChatModel> list_chat_models, Context context){
        this.list_chat_models = list_chat_models;
        this.context = context;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return list_chat_models.size();
    }

    @Override
    public Object getItem(int position) {
        return list_chat_models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
        {
            if (list_chat_models.get(position).isSend)
                view = layoutInflater.inflate(R.layout.lis_item, null);
            else
                view = layoutInflater.inflate(R.layout.list_item_rec, null);

            BubbleTextView text_message = (BubbleTextView)view.findViewById(R.id.user_message);
            text_message.setText(list_chat_models.get(position).message);
        }
        return view;
    }
}
