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

import com.myapplication.R;
import com.myapplication.data.model.Category;
import com.myapplication.helper.DBConnection;
//import com.myapplication.data.model.filteredCategory.FilteredCategoryList;

import java.util.ArrayList;
import java.util.List;

public class CategoryFilterAdapter extends RecyclerView.Adapter<CategoryFilterAdapter.Hold> {


    private Context context;
    private List<Category> allCategories = new ArrayList<>();
    private DBConnection db;
    private SharedPreferences preferences;

    public CategoryFilterAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Hold onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_filtered_categories_list, parent, false);
        db = new DBConnection(context);
        return new Hold(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Hold hold, int i) {

        hold.ItemFilteredCategoriesListTvCategory.setText(allCategories.get(i).getCategory());

    }

    @Override
    public int getItemCount() {
        return allCategories.size();
    }


    public void setData(ArrayList<Category> allCategories) {
        this.allCategories = allCategories;
    }


    class Hold extends RecyclerView.ViewHolder {
        TextView ItemFilteredCategoriesListTvCategory;
        ImageView ItemFilteredCategoriesListIbRemove;

        public Hold(@NonNull View itemView) {
            super(itemView);
            ItemFilteredCategoriesListTvCategory = itemView.findViewById(R.id.Item_Filtered_Categories_List_Tv_Category);
            ItemFilteredCategoriesListIbRemove = itemView.findViewById(R.id.Item_Filtered_Categories_List_Ib_Remove);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    preferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
                    String userId = preferences.getString("user_ID", "");
                    String category = allCategories.get(getAdapterPosition()).getCategory();
                    db.deleteCategory(category,userId);

                    ArrayList<Category> allCategories = db.getAllCategories(userId);
                    setData(allCategories);
                    notifyDataSetChanged();

                }
            });

        }
    }


}
