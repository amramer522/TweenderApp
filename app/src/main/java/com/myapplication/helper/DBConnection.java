package com.myapplication.helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.myapplication.data.model.Category;
import com.myapplication.data.model.Tweet;
import com.myapplication.data.model.User;
import com.myapplication.data.model.Word;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DBConnection extends SQLiteOpenHelper {
    public static final String DbName = "tweender";
    public static final int version = 13;
    private SharedPreferences preferences;

    public DBConnection(Context context) {
        super(context, DbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUsersTable = "create table " + "users" + " ( " +
                "user_id" + " INTEGER primary key autoincrement, " +
                "uname" + " text, " +
                "ugender" + " text, " +
                "uphone" + " text, " +
                "ustatus" + " text, " +
                "uphoto" + " text, " +
                "uemail" + " text, " +
                "upassword" + " text )";
        //                "uphoto" + " blob, " +


        String createTweetsTable = "create table " + "tweets" + " ( " +
                "tweet_id" + " INTEGER primary key autoincrement, " +
                "tweet_content" + " text, " +
                "tweet_created_time" + " text, " +
                "tweet_category" + " text, " +
                "user_id" + " text )";

        String createCategoriesTable = "create table " + "categories" + " ( " +
                "category_id" + " INTEGER primary key autoincrement, " +
                "user_id" + " text, " +
                "category" + " text )";

        String createWordsTable = "create table " + "words" + " ( " +
                "word_id" + " INTEGER primary key autoincrement, " +
                "word" + " text, " +
                "category" + " text, " +
                "user_id" + " text )";

        db.execSQL(createUsersTable);
        db.execSQL(createTweetsTable);
        db.execSQL(createCategoriesTable);
        db.execSQL(createWordsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropUsers = "Drop table users";
        db.execSQL(dropUsers);
        String dropTweets = "Drop table tweets";
        db.execSQL(dropTweets);
        String dropCategories = "Drop table categories";
        db.execSQL(dropCategories);
        String dropWords = "Drop table words";
        db.execSQL(dropWords);
        onCreate(db);
    }


    ///////////////////////////////////// user ///////////////////////////////////////
    // register
    // شغال
    // finish
    public void insertUser(Context context, User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("uname", user.getUname());
        contentValues.put("ugender", user.getUgender());
        contentValues.put("uphone", user.getUphone());
        contentValues.put("uemail", user.getUemail());
        contentValues.put("upassword", user.getUpassword());
        long row = db.insert("users", null, contentValues);
        if (row > 0) {
//            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
        }
    }

    // insert Profile Img
    // here we update the row by adding the img and the status to specific user_id
    //finish
    // شغال
    public void insertImageWithStatus(Context context, String user_ID, Uri uri, String status) {
        SQLiteDatabase db = this.getWritableDatabase();

//        String img_path = "/Camera/IMG20190504200116.jpg";
//        String img_path = uri.getLastPathSegment();
//        Toast.makeText(context, ""+img_path, Toast.LENGTH_SHORT).show();

//        Bitmap bitmapImage = null;
//        try {
//            bitmapImage = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // convert bitmap to byte
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        byte imageInByte[] = stream.toByteArray();

        // object contains the updated values
        ContentValues contentValues = new ContentValues();
        contentValues.put("ustatus", status);
//        contentValues.put("uphoto", imageInByte);
//        contentValues.put("uphoto", img_path);
        db.update("users", contentValues, "user_id = ? ", new String[]{user_ID});
    }

    // check if email exist
    // شغال
    // finish
    public boolean checkEmailExist(Context context, String email) {
        String[] columns = {"uemail", "uphone", "ugender", "uname", "upassword"};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "uemail=?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    // check if name exist
    // شغال
    // finish
    public boolean checkNameExist(Context context, String uname) {
        // array of columns to fetch
        String[] columns = {"uemail", "uphone", "ugender", "uname", "upassword"};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = "uname=?";
        // selection arguments
        String[] selectionArgs = {uname};
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    // login
    // شغال
    // finish
    public boolean checkUser(Context context, String uname, String upassword) {
        // array of columns to fetch
        String[] columns = {"uemail", "uphone", "ugender", "uname", "upassword"};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = "uname=? and upassword = ?";
        // selection arguments
        String[] selectionArgs = {uname, upassword};
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
//            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    //////////////////////////////// tweet ///////////////////////////////////////////////
    // add new Tweet
    // شغال
    // finish
    public void insertTweet(Context context, Tweet tweet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", tweet.getUser_id());
        contentValues.put("tweet_content", tweet.getTweet_content());
        contentValues.put("tweet_created_time", tweet.getTweet_created_time());
        long row = db.insert("tweets", null, contentValues);
        if (row > 0) {
//            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
        }

    }

    // get home tweets
    // شغال
    // finish
    public ArrayList<Tweet> getAllTweets()
    {
        String cols[] = {"tweet_id","tweet_category","tweet_content", "tweet_created_time", "user_id"};
        ArrayList<Tweet> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("tweets", cols, null, null, null, null, "tweet_id");
        if (cursor.moveToFirst()) {
            do {
                int tweet_id_index = cursor.getColumnIndex("tweet_id");
                String tweet_id = cursor.getString(tweet_id_index);
                int tweet_content_index = cursor.getColumnIndex("tweet_content");
                String tweet_content = cursor.getString(tweet_content_index);
                int tweet_created_time_index = cursor.getColumnIndex("tweet_created_time");
                String tweet_created_time = cursor.getString(tweet_created_time_index);
                int user_id_index = cursor.getColumnIndex("user_id");
                String user_id = cursor.getString(user_id_index);
                int tweet_category_index = cursor.getColumnIndex("tweet_category");
                String tweet_category = cursor.getString(tweet_category_index);
                arrayList.add(new Tweet(tweet_id, tweet_content, tweet_category,tweet_created_time, user_id));
            } while (cursor.moveToNext());
        }
        return arrayList;
    }



    public ArrayList<Tweet> getAllTweetsAfterFilter(String category)
    {
        String cols[] = {"tweet_id", "tweet_content","tweet_category", "tweet_created_time", "user_id"};
        ArrayList<Tweet> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = "tweet_category not like ?";
        String[] selectionArgs = {category};
        Cursor cursor = db.query("tweets", cols, selection, selectionArgs, null, null, "tweet_id");
        if (cursor.moveToFirst())
        {
            do {
                int tweet_id_index = cursor.getColumnIndex("tweet_id");
                String tweet_id = cursor.getString(tweet_id_index);
                int tweet_content_index = cursor.getColumnIndex("tweet_content");
                String tweet_content = cursor.getString(tweet_content_index);
                int tweet_created_time_index = cursor.getColumnIndex("tweet_created_time");
                String tweet_created_time = cursor.getString(tweet_created_time_index);
                int user_id_index = cursor.getColumnIndex("user_id");
                String user_id = cursor.getString(user_id_index);
                arrayList.add(new Tweet(tweet_id, tweet_content, tweet_created_time, user_id));
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    public ArrayList<Tweet> getAllTweetsOfCategory(String category)
    {
        String cols[] = {"tweet_id", "tweet_content","tweet_category", "tweet_created_time", "user_id"};
        ArrayList<Tweet> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = "tweet_category like ?";
        String[] selectionArgs = {category};
        Cursor cursor = db.query("tweets", cols, selection, selectionArgs, null, null, "tweet_id");
        if (cursor.moveToFirst())
        {
            do {
                int tweet_id_index = cursor.getColumnIndex("tweet_id");
                String tweet_id = cursor.getString(tweet_id_index);
                int tweet_content_index = cursor.getColumnIndex("tweet_content");
                String tweet_content = cursor.getString(tweet_content_index);
                int tweet_created_time_index = cursor.getColumnIndex("tweet_created_time");
                String tweet_created_time = cursor.getString(tweet_created_time_index);
                int user_id_index = cursor.getColumnIndex("user_id");
                String user_id = cursor.getString(user_id_index);
                arrayList.add(new Tweet(tweet_id, tweet_content, tweet_created_time, user_id));
            } while (cursor.moveToNext());
        }
        return arrayList;
    }


    //////////////////////////////////  word ////////////////////////////////////////////////
    // add new word
    // شغال
    // finish
    public void insertWord(Context context, Word word) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("word", word.getWord());
        contentValues.put("category", word.getCategory());
        contentValues.put("user_id", word.getUser_id());
        long row = db.insert("words", null, contentValues);
        if (row > 0) {
//            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
        }

    }


    // check if word of this category exist before for current user
    // finish
    // شغال
    public boolean checkwordExist(Context context, String word, String category, String user_id) {
        // array of columns to fetch
        String[] columns = {"word", "category", "word_id", "user_id"};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = "word=? and category = ? and user_id = ?";
        // selection arguments
        String[] selectionArgs = new String[]{word, category, user_id};
        Cursor cursor = db.query("words", columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    // delete word
    // شغال
    // finish
    public void deleteWord(String word, String category, String user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("words", "word=? and category = ? and user_id = ?", new String[]{word, category, user_id});
//        db.execSQL("delete from categories where category=" + category);
    }

    // get all word filtered
    // شغال
    // finish
    public ArrayList<Word> getAllWords(String userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String cols[] = {"word", "category", "word_id", "user_id"};
        String selection = "user_id like ?";
        String[] selectionArgs = {userID};
        ArrayList<Word> arrayList = new ArrayList<>();
        Cursor cursor = db.query("words", cols, selection, selectionArgs, null, null, "word_id");
        if (cursor.moveToFirst()) {
            do {
                int word_id_index = cursor.getColumnIndex("word_id");
                String word_id = cursor.getString(word_id_index);
                int word_index = cursor.getColumnIndex("word");
                String word = cursor.getString(word_index);
                int category_index = cursor.getColumnIndex("category");
                String category = cursor.getString(category_index);
                int user_id_index = cursor.getColumnIndex("user_id");
                String user_id = cursor.getString(user_id_index);
                arrayList.add(new Word(word_id, word, category, user_id));
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    /////////////////////////////// category ///////////////////////////////////////
    // add new category to the user id
    // شغال
    // finish
    public void insertCategory(Context context, Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("category_id", category.getCategory_id());
        contentValues.put("category", category.getCategory());
        contentValues.put("user_id", category.getUser_id());
        long row = db.insert("categories", null, contentValues);
        if (row > 0) {
//            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
        }

    }


    // check this category exist before in the same user id
    // شغال
    // finish
    public boolean checkCategoryExist(Context context, String category, String userId) {
        // array of columns to fetch
        String[] columns = {"category_id", "category", "user_id"};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria

        String selection = "category=? and user_id = ?";
        // selection arguments
        String[] selectionArgs = new String[]{category, userId};
        Cursor cursor = db.query("categories", columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    //delete category
    // شغال
    // finish
    public void deleteCategory(String category, String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("categories", "category=? and user_id = ?", new String[]{category, userId});
//        db.execSQL("delete from categories where category=" + category);
    }


    // get all categories filtered for the user id
    // شغال
    // finish
    public ArrayList<Category> getAllCategories(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = "user_id like ?";
        String[] selectionArgs = {userId};
        String cols[] = {"category_id", "category", "user_id"};
        ArrayList<Category> arrayList = new ArrayList<>();

        Cursor cursor = db.query("categories", cols, selection, selectionArgs, null, null, "category_id");
        if (cursor.moveToFirst()) {
            do {
                int category_id_index = cursor.getColumnIndex("category_id");
                String category_id = cursor.getString(category_id_index);
                int category_index = cursor.getColumnIndex("category");
                String category = cursor.getString(category_index);
                int user_id_index = cursor.getColumnIndex("user_id");
                String user_id = cursor.getString(user_id_index);
                arrayList.add(new Category(category_id, category, user_id));
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

///////////////////////////////////////////////////////////////////////////////////

    // get id to save to shared Preferences
    // شغال
    // finsih
    public String getUserId(Context context, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = new User();
        String[] columns = {"user_id", "uemail", "uphone", "ugender", "uname", "upassword", "ustatus"};
        String selection = "uname like ?";
        String[] selectionArgs = {name};
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            String user_id = cursor.getString(0);
            return user_id;
        }
        return "";
    }


    // get user information by the user id
    // work
    // finish
    public User getUserObject(Context context, String userID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = new User();
        String[] columns = {"user_id", "uemail", "uphone", "ugender", "uname", "ustatus", "uphoto","upassword"};
        String selection = "user_id like ?";
        String[] selectionArgs = {userID};
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst())
        {
            String user_id = cursor.getString(0);
            String uemail = cursor.getString(1);
            String uphone = cursor.getString(2);
            String ugender = cursor.getString(3);
            String uname = cursor.getString(4);
            String ustatus = cursor.getString(5);
//            byte[] blob = cursor.getBlob(6);
            String img_pth = cursor.getString(6);

//            Bitmap bitmap_img = BitmapFactory.decodeByteArray(blob, 0, blob.length);
//            user = new User(user_id, uname, uphone, uemail, ugender, ustatus, bitmap_img);
            user = new User(user_id, uname, uphone, uemail, ugender, ustatus, img_pth);


            return user;

        }
        return null;
    }


    public void setIntialTweets()
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("tweet_content", "It's the original fantasy football game. They're relaunching a new look #FantasyLeague auction game for the 2019/20 Premier League season. ??");
        contentValues1.put("tweet_category", "football");
        contentValues1.put("user_id", "1");
        db.insert("tweets", null, contentValues1);

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("tweet_content", "Lionel Messi has scored 45+ goals in eight separate seasons as a professional: 2018/19: 45 goals ?2017/18: 45 goals ?2016/17: 54 goals ?2014/15: 58 goals ?2012/13: 60 goals ?2011/12: 73 goals ?2010/11: 53 goals ?2009/10: 47 goals ?");
        contentValues2.put("tweet_category", "football");
        contentValues2.put("user_id", "1");
        db.insert("tweets", null, contentValues2);


        ContentValues contentValues3 = new ContentValues();
        contentValues3.put("tweet_content", "No matter what has happened. No matter what you’ve done. No matter what you will do. I will always love you. I swear it.");
        contentValues3.put("tweet_category", "love");
        contentValues3.put("user_id", "1");
        db.insert("tweets", null, contentValues3);


        ContentValues contentValues4 = new ContentValues();
        contentValues4.put("tweet_content", "I want everyone to meet you. You’re my favorite person of all time.");
        contentValues4.put("tweet_category", "love");
        contentValues4.put("user_id", "1");
        db.insert("tweets", null, contentValues4);


        ContentValues contentValues5 = new ContentValues();
        contentValues5.put("tweet_content", "The key to artificial intelligence has always been the representation.");
        contentValues5.put("tweet_category", "AI");
        contentValues5.put("user_id", "1");
        db.insert("tweets", null, contentValues5);



        ContentValues contentValues6 = new ContentValues();
        contentValues6.put("tweet_content", "Females will find out everything, so I suggest you don't lie to them.");
        contentValues6.put("tweet_category", "love");
        contentValues6.put("user_id", "1");
        db.insert("tweets", null, contentValues6);



        ContentValues contentValues7 = new ContentValues();
        contentValues7.put("tweet_content", "The 2018 World Cup was not only a success for England fans but memorable for those who like to complain that modern technology is killing football. Yes, VAR was used. ");
        contentValues7.put("tweet_category", "football");
        contentValues7.put("user_id", "1");
        db.insert("tweets", null, contentValues7);




        ContentValues contentValues8 = new ContentValues();
        contentValues8.put("tweet_content", "CORRECT DECISION! The goal is given. Tottenham are going through as it stands. #MCITOT #UCL");
        contentValues8.put("tweet_category", "football");
        contentValues8.put("user_id", "1");
        db.insert("tweets", null, contentValues8);




        ContentValues contentValues9 = new ContentValues();
        contentValues9.put("tweet_content", "Tottenham's first three fixtures in the Champions League: ?Inter 2-1 Spurs ?Spurs 2-4 Barcelona ??PSV 2-2 Spurs And now they're in the semi-finals against Ajax. What a turnaround. ??");
        contentValues9.put("tweet_category", "football");
        contentValues9.put("user_id", "1");
        db.insert("tweets", null, contentValues9);




        ContentValues contentValues10 = new ContentValues();
        contentValues10.put("tweet_content", "Artificial intelligence is growing up fast, as are robots whose facial expressions can elicit empathy and make your mirror neurons quiver.");
        contentValues10.put("tweet_category", "AI");
        contentValues10.put("user_id", "1");
        db.insert("tweets", null, contentValues10);




        ContentValues contentValues11 = new ContentValues();
        contentValues11.put("tweet_content", "I've lived I've loved I've lost I've missed I've hurt I've trusted I've made mistakes but most of all, I've learned.");
        contentValues11.put("tweet_category", "football");
        contentValues11.put("user_id", "1");
        db.insert("tweets", null, contentValues11);




        ContentValues contentValues12 = new ContentValues();
        contentValues12.put("tweet_content", "Every artist was first an amateur");
        contentValues12.put("tweet_category", "Art");
        contentValues12.put("user_id", "1");
        db.insert("tweets", null, contentValues12);



        ContentValues contentValues13 = new ContentValues();
        contentValues13.put("tweet_content", "Pep Guardiola in the Champions League for Manchester City: ??2017: ?Man City 6-6 Monaco ??2018: ?Man City 1-5 Liverpool ??2019: ?Man City 4-4 Spurs ??Over half a billion spent. ??Not got further in the competition than David Moyes");
        contentValues13.put("tweet_category", "football");
        contentValues13.put("user_id", "1");
        db.insert("tweets", null, contentValues13);



        ContentValues contentValues14 = new ContentValues();
        contentValues14.put("tweet_content", "The chemistry involved made everything Factory did quite special.");
        contentValues14.put("tweet_category", "chemisrty");
        contentValues14.put("user_id", "1");
        db.insert("tweets", null, contentValues14);




        ContentValues contentValues15 = new ContentValues();
        contentValues15.put("tweet_content", "Every child is an artist. The problem is how to remain an artist once we grow up");
        contentValues15.put("tweet_category", "Art");
        contentValues15.put("user_id", "1");
        db.insert("tweets", null, contentValues15);



        ContentValues contentValues16 = new ContentValues();
        contentValues16.put("tweet_content", "To reason with governments, as they have existed for ages, is to argue with brutes. It is only from the nations themselves that reforms can be expected.");
        contentValues16.put("tweet_category", "policy");
        contentValues16.put("user_id", "1");
        db.insert("tweets", null, contentValues16);



        ContentValues contentValues17 = new ContentValues();
        contentValues17.put("tweet_content", "New York Red Bulls are interested in making Thierry Henry their head coach. (Source: SkySports)");
        contentValues17.put("tweet_category", "football");
        contentValues17.put("user_id", "1");
        db.insert("tweets", null, contentValues17);



        ContentValues contentValues18 = new ContentValues();
        contentValues18.put("tweet_content", "It’s not about having the perfect relationship, it’s about finding someone who matches you and will go through everything without giving up.");
        contentValues18.put("tweet_category", "love");
        contentValues18.put("user_id", "1");
        db.insert("tweets", null, contentValues18);



        ContentValues contentValues19 = new ContentValues();
        contentValues19.put("tweet_content", "Positive thoughts and positive feelings are what make your happiness. You are in control of your happiness, always.");
        contentValues19.put("tweet_category", "love");
        contentValues19.put("user_id", "1");
        db.insert("tweets", null, contentValues19);



        ContentValues contentValues20 = new ContentValues();
        contentValues20.put("tweet_content", "We want people to understand that they’re choosing a set of policies and principles, a way of life, not a person.");
        contentValues20.put("tweet_category", "policy");
        contentValues20.put("user_id", "1");
        db.insert("tweets", null, contentValues20);



        ContentValues contentValues21 = new ContentValues();
        contentValues21.put("tweet_content", "No inanimate object is ever fully determined by the laws of physics and chemistry.");
        contentValues21.put("tweet_category", "chemisrty");
        contentValues21.put("user_id", "1");
        db.insert("tweets", null, contentValues21);



        ContentValues contentValues22 = new ContentValues();
        contentValues22.put("tweet_content", ".The 2018 World Cup was not only a success for England fans but memorable for those who like to complain that modern technology is killing football. Yes, VAR was used.");
        contentValues22.put("tweet_category", "football");
        contentValues22.put("user_id", "1");
        db.insert("tweets", null, contentValues22);


        ContentValues contentValues23 = new ContentValues();
        contentValues23.put("tweet_content", "The grounding in natural sciences which I obtained in the course of my medical studies, including preliminary examinations in botany, zoology, physics, and chemistry, was to become decisive in determining the trend of my literary work.");
        contentValues23.put("tweet_category", "chemisrty");
        contentValues23.put("user_id", "1");
        db.insert("tweets", null, contentValues23);


        ContentValues contentValues24 = new ContentValues();
        contentValues24.put("tweet_content", "If my love were an ocean, there would be no more land. If my love were a desert, you would see only sand. If my love were a star–late at night, only light. And if my love could grow wings, I’d be soaring in flight.");
        contentValues24.put("tweet_category", "love");
        contentValues24.put("user_id", "1");
        db.insert("tweets", null, contentValues24);


        ContentValues contentValues25 = new ContentValues();
        contentValues25.put("tweet_content", "OH MY GOD! IT'S BEEN DISALLOWED! VAR HAS RULED IT OUT! THIS IS INCREDIBLE!");
        contentValues25.put("tweet_category", "football");
        contentValues25.put("user_id", "1");
        db.insert("tweets", null, contentValues25);


        ContentValues contentValues26 = new ContentValues();
        contentValues26.put("tweet_content", "Honesty is the best Policy is the Capitalistic thought as it gives free of cost Security to their assets and to their investments........");
        contentValues26.put("tweet_category", "policy");
        contentValues26.put("user_id", "1");
        db.insert("tweets", null, contentValues26);


        ContentValues contentValues27 = new ContentValues();
        contentValues27.put("tweet_content", "Mohamed Salah's club record since the start of last season... - Games: 97 - Goals: 66 - Assists: 26 If the Egyptian King scores against Cardiff this Sunday, The Sportsman will be giving away £100 in CASH to one of you for FREE! Opt-In below. #LFC #CARLIV");
        contentValues27.put("tweet_category", "football");
        contentValues27.put("user_id", "1");
        db.insert("tweets", null, contentValues27);


        ContentValues contentValues28 = new ContentValues();
        contentValues28.put("tweet_content", "A true relationship is when you can tell each other anything and everything. No secrets and no lies.");
        contentValues28.put("tweet_category", "love");
        contentValues28.put("user_id", "1");
        db.insert("tweets", null, contentValues28);


        ContentValues contentValues29 = new ContentValues();
        contentValues29.put("tweet_content", "Recent advancements in AI, and specifically in machine learning, have contributed to the growth of Autonomous Things such as drones and self-driving cars.");
        contentValues29.put("tweet_category", "AI");
        contentValues29.put("user_id", "1");
        db.insert("tweets", null, contentValues29);



        ContentValues contentValues30 = new ContentValues();
        contentValues30.put("tweet_content", "Hi guys! CR7 Footwear has a new website! Feel legendary with our spring/summer collection!");
        contentValues30.put("tweet_category", "football");
        contentValues30.put("user_id", "1");
        db.insert("tweets", null, contentValues30);


        ContentValues contentValues31 = new ContentValues();
        contentValues31.put("tweet_content", "Every band needs it's own special chemistry. And Bez was a very good chemist.");
        contentValues31.put("tweet_category", "chemisrty");
        contentValues31.put("user_id", "1");
        db.insert("tweets", null, contentValues31);



        ContentValues contentValues32 = new ContentValues();
        contentValues32.put("tweet_content", "Natural language processing[77] gives machines the ability to read and understand human language and extract intelligence from it.");
        contentValues32.put("tweet_category", "AI");
        contentValues32.put("user_id", "1");
        db.insert("tweets", null, contentValues32);


        ContentValues contentValues33 = new ContentValues();
        contentValues33.put("tweet_content", "I've lived I've loved I've lost I've missed I've hurt I've trusted I've made mistakes but most of all, I've learned.");
        contentValues33.put("tweet_category", "love");
        contentValues33.put("user_id", "1");
        db.insert("tweets", null, contentValues33);


        ContentValues contentValues34 = new ContentValues();
        contentValues34.put("tweet_content", "During my McGill years, I took a number of math courses, more than other students in chemistry.");
        contentValues34.put("tweet_category", "chemisrty");
        contentValues34.put("user_id", "1");
        db.insert("tweets", null, contentValues34);

        ContentValues contentValues35 = new ContentValues();
        contentValues35.put("tweet_content", "Manchester United's contract talks with Marcus Rashford are stalling because the forward is demanding a wage in excess of £200,000 a week. (Source: Daily Mail)");
        contentValues35.put("tweet_category", "football");
        contentValues35.put("user_id", "1");
        db.insert("tweets", null, contentValues35);


        ContentValues contentValues36 = new ContentValues();
        contentValues36.put("tweet_content", "GOAL: FC Porto 1 - 4 Liverpool (1-6 agg). Virgil Van Dijk gets in on the action. #PORLIV #UCL");
        contentValues36.put("tweet_category", "football");
        contentValues36.put("user_id", "1");
        db.insert("tweets", null, contentValues36);


        ContentValues contentValues37 = new ContentValues();
        contentValues37.put("tweet_content", "Digital imaging is as much about chemistry as it is about semiconductors.");
        contentValues37.put("tweet_category", "chemisrty");
        contentValues37.put("user_id", "1");
        db.insert("tweets", null, contentValues37);


        ContentValues contentValues38 = new ContentValues();
        contentValues38.put("tweet_content", "Countries legislate out creativity.");
        contentValues38.put("tweet_category", "policy");
        contentValues38.put("user_id", "1");
        db.insert("tweets", null, contentValues38);

        ContentValues contentValues39 = new ContentValues();
        contentValues39.put("tweet_content", "Excited to show you my new Nike Mercurial Superfly 360 LVL UP ?This design combines some of the best Mercurial’s I’ve had. I can’t wait to wear it once I’ve recovered from my injury. Until then, you’ll see it on pitch with the newest icons of speed @samkerr1 and @KMbappe");
        contentValues39.put("tweet_category", "football");
        contentValues39.put("user_id", "1");
        db.insert("tweets", null, contentValues39);

        ContentValues contentValues40 = new ContentValues();
        contentValues40.put("tweet_content", "At Harvard, I majored in chemistry with a strong inclination toward math.");
        contentValues40.put("tweet_category", "chemisrty");
        contentValues40.put("user_id", "1");
        db.insert("tweets", null, contentValues40);




    }

    public boolean checkwordExist(Context context, String word, String user_id) {
        // array of columns to fetch
        String[] columns = {"word", "category", "word_id", "user_id"};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = "word=? and user_id = ?";
        // selection arguments
        String[] selectionArgs = new String[]{word, user_id};
        Cursor cursor = db.query("words", columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
}