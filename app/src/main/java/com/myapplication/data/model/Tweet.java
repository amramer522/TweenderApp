package com.myapplication.data.model;

public class Tweet
{
 private String tweet_id,tweet_content,tweet_created_time,user_id,tweet_category;

    public Tweet(String tweet_id, String tweet_content, String tweet_category, String tweet_created_time, String user_id)
    {
        this.tweet_id = tweet_id;
        this.tweet_content = tweet_content;
        this.tweet_created_time = tweet_created_time;
        this.user_id = user_id;
        this.tweet_category=tweet_category;
    }

    public String getTweet_category()
    {
        return tweet_category;
    }

    public void setTweet_category(String tweet_category) {
        this.tweet_category = tweet_category;
    }

    public Tweet()
    {
    }

    public Tweet(String tweet_id, String tweet_content, String tweet_created_time, String user_id) {
        this.tweet_id = tweet_id;
        this.tweet_content = tweet_content;
        this.tweet_created_time = tweet_created_time;
        this.user_id = user_id;
    }

    public String getTweet_id() {
        return tweet_id;
    }

    public String getTweet_content() {
        return tweet_content;
    }

    public void setTweet_content(String tweet_content) {
        this.tweet_content = tweet_content;
    }

    public String getTweet_created_time() {
        return tweet_created_time;
    }

    public void setTweet_created_time(String tweet_created_time) {
        this.tweet_created_time = tweet_created_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
