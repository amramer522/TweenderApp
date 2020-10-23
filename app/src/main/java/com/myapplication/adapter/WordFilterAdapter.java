package com.myapplication.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myapplication.R;
import com.myapplication.data.model.Category;
import com.myapplication.data.model.Word;
import com.myapplication.helper.DBConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import butterknife.BindView;

public class WordFilterAdapter extends RecyclerView.Adapter<WordFilterAdapter.Hold> {


    private Context context;
    private List<Word> wordList = new ArrayList<>();
    private DBConnection db;
    private SharedPreferences preferences;

    public WordFilterAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public Hold onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_filtered_words_list, parent, false);
        db = new DBConnection(context);
        return new Hold(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Hold hold, int i) {
        hold.ItemFilteredWordsListTvWord.setText(wordList.get(i).getWord());
        hold.ItemFilteredWordsListTvCategory.setText(wordList.get(i).getCategory());
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public void setData(List<Word> wordList)
    {
        this.wordList = wordList;
    }

    class Hold extends RecyclerView.ViewHolder {
        TextView ItemFilteredWordsListTvWord;
        TextView ItemFilteredWordsListTvCategory;
        ImageView ItemFilteredWordsListIbRemove;

        public Hold(@NonNull View itemView) {
            super(itemView);
            ItemFilteredWordsListTvWord = itemView.findViewById(R.id.Item_Filtered_Words_List_Tv_Word);
            ItemFilteredWordsListTvCategory = itemView.findViewById(R.id.Item_Filtered_Words_List_Tv_Category);
            ItemFilteredWordsListIbRemove = itemView.findViewById(R.id.Item_Filtered_Words_List_Ib_Remove);
            ItemFilteredWordsListIbRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    String word = wordList.get(getAdapterPosition()).getWord();
                    String category = wordList.get(getAdapterPosition()).getCategory();
                    preferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
                    String userId = preferences.getString("user_ID", "");
                    db.deleteWord(word,category,userId);
                    ArrayList<Word> wordArrayList = db.getAllWords(userId);
                    setData(wordArrayList);
                    notifyDataSetChanged();
                }
            });

        }

    }
}
