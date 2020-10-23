package com.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.myapplication.R;
import com.myapplication.data.model.Tweet;
import com.myapplication.data.model.User;
import com.myapplication.helper.DBConnection;
import com.myapplication.helper.HelperMethod;
import com.myapplication.ui.activity.DetailesActivity;
import com.myapplication.ui.fragment.homeCycle.HomeFragment;
import com.myapplication.ui.fragment.homeCycle.ProfileFragment;
import com.myapplication.ui.fragment.userCycle.ResetPasswordFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;


import static com.myapplication.helper.HelperMethod.showToastMsg;

public class FilteredRecycleViewAdapter extends RecyclerView.Adapter<FilteredRecycleViewAdapter.Hold> {

    private Context context;
    private List<Tweet> dataList = new ArrayList<>();
    private DBConnection db;
    private User user = new User();
    Date date = new Date();

    public FilteredRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Hold onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        db = new DBConnection(context);
        return new Hold(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Hold hold, int i) {
        hold.tweet_content.setText(dataList.get(i).getTweet_content());
//        Date currentDate = Calendar.getInstance().getTime();
//        String strDateFormat = "YYYY-MM-DD HH:MM:SS";
////                String strDateFormat = "hh:mm a";
//        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
//        String current_time = dateFormat.format(date);
//        Date tweet_created_time =  dataList.get(i).getTweet_created_time();

        hold.tweet_time.setText(dataList.get(i).getTweet_created_time());

//        user = db.getUserObject(context, dataList.get(i).getUser_id());
//        hold.UserName.setText(user.getUname());
//        getBitmapFromPath(user.getUser_img_path());
//        hold.UserImage.setImageBitmap(user.getBitmap_img());
//        hold.UserImage.setImageResource(R.drawable);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setData(List<Tweet> dataList) {
        this.dataList = dataList;
    }


    class Hold extends RecyclerView.ViewHolder {
        TextView UserName;
        TextView tweet_content;
        TextView tweet_time;
//        CircleImageView UserImage;

        public Hold(@NonNull final View itemView) {
            super(itemView);
            tweet_content = itemView.findViewById(R.id.Item_Home_Tv_Content_Text);
            tweet_time = itemView.findViewById(R.id.Item_Home_Tv_Time);
//            UserImage = itemView.findViewById(R.id.Item_Home_Civ_User_Image);
            UserName = itemView.findViewById(R.id.Item_Home_Tv_User_Name);


//            UserImage.setOnClickListener(new View.OnClickListener()
//            {
//                @Override
//                public void onClick(View v)
//                {
//                    Intent i = new Intent(context, DetailesActivity.class);
//                    i.putExtra("profile_user_id",user.getUid());
//                    context.startActivity(i);
//                }
//            });

//            itemView.setOnClickListener(new View.OnClickListener()
//            {
//                @Override
//                public void onClick(View v)
//                {
//                    showToastMsg(context,"onclick");
//                    PopupMenu popupMenu = new PopupMenu(context,itemView);
//                    popupMenu.getMenuInflater().inflate(R.menu.tweet_popup,popupMenu.getMenu());
//                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem item)
//                        {
//                            showToastMsg(context,"item"+getAdapterPosition());
//                            return true;
//                        }
//                    });
//                }
//            });


//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v)
//                {
//                    PopupMenu popupMenu = new PopupMenu(context,itemView);
//                    popupMenu.getMenuInflater().inflate(R.menu.tweet_popup,popupMenu.getMenu());
//                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem item) {
//                            showToastMsg(context,"item"+getAdapterPosition());
//                            return true;
//                        }
//                    });
//                    return true;
//                }
//            });
        }
    }
}