package com.myapplication.data.rest;




import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

//    //    // Register
//    @POST("register.php")
//    @FormUrlEncoded
//    Call<User> createAccount(@Field("rname") String name,
//                             @Field("remail") String email,
//                             @Field("rphone") String phone,
//                             @Field("rgender") String gender,
//                             @Field("rpassword") String password,
//                             @Field("rpassword_confirmation") String password_confirmation
//    );
//
//
//    //    //done and work
//    // Login
//    @POST("login.php")
//    @FormUrlEncoded
//    Call<Login> login(@Field("remail") String email,
//                      @Field("rpassword") String password);
//
//
////    // get tweets
////    @POST("retriveProfileTweets.php")
////    @FormUrlEncoded
////    Call<List<Tweet>> getTweets(@Field("raccess_token") String access_token
////    );
//
////    @GET("reviveGategoryList.php")
////    Call<List<FilteredCategoryList>> getfilteredCategoryList();
//
//
//    @GET("retriveAllTweets.php")
//    Call<List<AllTweet>> getAllTweets();
////
////
////    @POST("addNewCategory.php")
////    @FormUrlEncoded
////    Call<FilteredCategoryList> addNewGategory(@Field("user_id") String user_id,
////                      @Field("category_name") String category_name);
////
////
//
//
////    @Multipart
////    @POST("user/updateprofile")
////    Observable<> uploadImage(@Part("user_id") String id,
////                             @Part MultipartBody.Part image);
//
//
////    //  Donation Request
////    @GET("donation-requests")
////    Call<DonationRequests> donationRequest(@Query("api_token") String api_token);


}
