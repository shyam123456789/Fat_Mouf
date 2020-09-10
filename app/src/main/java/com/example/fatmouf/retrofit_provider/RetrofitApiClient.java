package com.example.fatmouf.retrofit_provider;


import com.example.fatmouf.models.ResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitApiClient {

    @POST("login")
    @FormUrlEncoded
    Call<ResponseModel> login(@Field("email") String email, @Field("password") String password);

    @POST("forgot-password")
    @FormUrlEncoded
    Call<ResponseModel> forgot_password(@Field("email") String email);

    @POST("change-password")
    @FormUrlEncoded
    Call<ResponseModel> change_password(@Field("Authorization") String token,
                                        @Field("password") String password,
                                        @Field("confirm_password") String confirm_password);





/*

    @POST("insertdatabankdetail")
    @FormUrlEncoded
    Call<MainModel> AddBankDetails(@Field("user_id") String user_id,
                                   @Field("bankname") String bankname,
                                   @Field("accountnumber") String accountnumber,
                                   @Field("ifsccode") String ifsccode);

    @POST("insertdatapaytmnumber")
    @FormUrlEncoded
    Call<MainModel> AddPaytmnumber(@Field("user_id") String user_id,
                                   @Field("paytm_number") String paytm_number);

    @POST("FCMtokenUpdate")
    @FormUrlEncoded
    Call<MainModel> addfcm(@Field("user_id") String user_id, @Field("fcm") String fcm);

    @POST("GetFavUserIdWise")
    @FormUrlEncoded
    Call<FavoriteCategoryModel> getFavoriteCategory(@Field("user_id") String user_id, @Field("slug") String slug);

    @POST("GetFavUserIdWise")
    @FormUrlEncoded
    Call<FavoriteStoreModel> getFavoriteStore(@Field("user_id") String user_id, @Field("slug") String slug);

    @POST("GetFavUserIdWise")
    @FormUrlEncoded
    Call<FavoriteOfferModel> getFavoriteOffer(@Field("user_id") String user_id, @Field("slug") String slug);

    @POST("favcategory")
    @FormUrlEncoded
    Call<MainModel> favcategory(@Field("user_id") String user_id);

    @POST("insertdataupidetail")
    @FormUrlEncoded
    Call<MainModel> AddUpinumber(@Field("user_id") String user_id,
                                 @Field("upi_detail") String paytm_number);

    @POST("favcategory")
    @FormUrlEncoded
    Call<MainModel> AddtoFavouriteCategory(@Field("user_id") String user_id,
                                           @Field("favcategories") String favcategories);

    @POST("favstore")
    @FormUrlEncoded
    Call<MainModel> AddtoFavouriteStore(@Field("user_id") String user_id,
                                        @Field("favstores") String favstores);

    @POST("favoffers")
    @FormUrlEncoded
    Call<MainModel> AddtoFavouriteOffer(@Field("user_id") String user_id,
                                        @Field("favoffers") String favoffers);

    @POST("insertdataformwise/contact")
    @FormUrlEncoded
    Call<MainModel> ContactUs(
            @Field("user_id") String user_id,
            @Field("cat_id") String cat_id,
            @Field("msg") String msg);

    @POST("insertdataformwise/feedback")
    @FormUrlEncoded
    Call<MainModel> feedback(
            @Field("user_id") String user_id,
            @Field("cat_id") String cat_id,
            @Field("msg") String msg);

    @GET("GetFeedbackCategory")
    Call<FeedBackContactUsCategory> Getfeedbackcategory();


    @GET("GetContactCategory")
    Call<FeedBackContactUsCategory> GetContactCategory();


    @POST("GetOffersTypeWise")
    @FormUrlEncoded
    Call<OffersModel> GetOffersTypeWise(@Field("slug") String slug);

    @POST("GetCategoryIdWise")
    @FormUrlEncoded
    Call<ViewCategoryModel> ViewCategory(@Field("cat_id") String category_id);

    @POST("GetStoresIdWise")
    @FormUrlEncoded
    Call<ViewStoreModel> ViewStore(@Field("store_id") String store_id);


    @POST("Getcategory")
    @FormUrlEncoded
    Call<CategoryListModel> getcategory(@Field("offset") String offset,
                                        @Field("user_id") String user_id,
                                        @Field("last_id") String last_id);

    @POST("Getstores")
    @FormUrlEncoded
    Call<StoreListModel> getstore(@Field("offset") String offset,
                                  @Field("user_id") String user_id,
                                  @Field("last_id") String last_id);

    @POST("updateuserdetail")
    @FormUrlEncoded
    Call<LogInModel> saveuserdetails(@Field("user_id") String userID,
                                     @Field("email") String email,
                                     @Field("fullname") String fullname,
                                     @Field("dob") String dob,
                                     @Field("phone") String mobile,
                                     @Field("reference") String referralcode,
                                     @Field("gender") String gender);


    @POST("GetRateUserWise")
    @FormUrlEncoded
    Call<MainModel> rateUs(@Field("user_id") String userID, @Field("rate_us") String rate);

    @POST("UpdateProfilePicture")
    @Multipart
    Call<MainModel> UpdateProfilePicture(@Part("user_id") RequestBody user_id,
                                         @Part MultipartBody.Part file);

    @GET("GetAppInstallation")
    Call<ApplicationMainModel> getapplications();

    @GET("GetOffersSliders")
    Call<SliderMainModel> getOfferSlider();
*/

}