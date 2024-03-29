package com.fatmouf.fatmouf.retrofit_provider;


import com.fatmouf.fatmouf.models.AddActivityModel;
import com.fatmouf.fatmouf.models.ContentModel;
import com.fatmouf.fatmouf.models.GroupListModel;
import com.fatmouf.fatmouf.models.HomePublicResponse;
import com.fatmouf.fatmouf.models.LoginResponse;
import com.fatmouf.fatmouf.models.ParticipantasListModel;
import com.fatmouf.fatmouf.models.ResponseModel;
import com.fatmouf.fatmouf.models.UpdateUserModel;
import com.fatmouf.fatmouf.models.UserListModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitApiClient {

    @POST("login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @POST("social_login")
    @FormUrlEncoded
    Call<LoginResponse> loginGmail(@Field("email") String email,
                                   @Field("password") String password);

    @POST("forgot-password")
    @FormUrlEncoded
    Call<ResponseModel> forgot_password(@Field("email") String email);

    @POST("get_group_detail")
    @FormUrlEncoded
    Call<ResponseModel> get_group_detail(@Header("Authorization") String token,
                                         @Field("group_id") String group_id);

    @POST("change-password")
    @FormUrlEncoded
    Call<ResponseModel> change_password(@Header("Authorization") String token,
                                        @Field("password") String password,
                                        @Field("confirm_password") String confirm_password);

    @GET("user_list")
    Call<UserListModel> user_list(@Header("Authorization") String token);

    @POST("accept_challenge")
    @FormUrlEncoded
    Call<ResponseModel> accept_challenge(@Header("Authorization") String token,
                                         @Field("challenge_id") String challenge_id,
                                         @Field("is_accepted") String is_accepted // pass 1 for accept
    );

    @POST("get_participant_list")
    @FormUrlEncoded
    Call<ParticipantasListModel> get_participant_list(@Header("Authorization") String token,
                                                      @Field("challenge_id") String challenge_id
    );

    @POST("get_activity_list")
    @FormUrlEncoded
    Call<ResponseModel> get_activity_list(@Header("Authorization") String token,
                                          @Field("challenge_id") String challenge_id
    );

    @POST("add_activity")
    @FormUrlEncoded
    Call<AddActivityModel> add_activity(@Header("Authorization") String token,
                                        @Field("challenge_id") String challenge_id,
                                        @Field("description") String description
    );

    @POST("update_participant")
    @FormUrlEncoded
    Call<ResponseModel> update_participant(@Header("Authorization") String token,
                                           @Field("is_winner") String is_winner,
                                           @Field("is_reported") String is_reported,
                                           @Field("reported_user") String reported_user,
                                           @Field("report_reason") String report_reason,
                                           @Field("participant_id") String participant_id // pass 1 for accept
    );


    @GET("get_group_list")
    Call<GroupListModel> get_group_list(@Header("Authorization") String token);

    @GET("public_list")
    Call<HomePublicResponse> public_list(@Header("Authorization") String token);

    @GET("get_myChallenge_list")
    Call<HomePublicResponse> get_myChallenge_list(@Header("Authorization") String token);

    @GET("get_my_accepted_challenge_list")
    Call<HomePublicResponse> get_my_accepted_challenge_list(@Header("Authorization") String token);

    @GET("get_content_management")
    Call<ContentModel> get_content_management(@Header("Authorization") String token);


    @GET("private_list")
    Call<HomePublicResponse> private_list(@Header("Authorization") String token);

    @POST("register")
    @Multipart
    Call<ResponseModel> register(@Part("first_name") RequestBody first_name,
                                 @Part("last_name") RequestBody last_name,
                                 @Part("email") RequestBody email,
                                 @Part("phonenumber") RequestBody phonenumber,
                                 @Part("password") RequestBody password,
                                 @Part("address") RequestBody address,
                                 @Part("lat") RequestBody lat,
                                 @Part("lng") RequestBody lng,
                                 @Part("token") RequestBody token,
                                 @Part("device_id") RequestBody device_id,
                                 @Part("refer_code") RequestBody refer_code,
                                 @Part MultipartBody.Part file//image
    );

    @POST("add_activity_media")
    @Multipart
    Call<ResponseModel> add_activity_mediaIMAGE(@Header("Authorization") String authorization,
                                                @Part("challenge_id") RequestBody challenge_id,
                                                @Part("media_type") RequestBody media_type,
                                                @Part("activity_id") RequestBody activity_id,
                                                @Part MultipartBody.Part imagefile//image
    );

    @POST("add_activity_media")
    @Multipart
    Call<ResponseModel> add_activity_mediaVIDEO(@Header("Authorization") String authorization,
                                                @Part("challenge_id") RequestBody challenge_id,
                                                @Part("media_type") RequestBody media_type,
                                                @Part("activity_id") RequestBody activity_id,
                                                @Part MultipartBody.Part videofile//video
    );

    @POST("add_challenge")
    @FormUrlEncoded
    Call<ResponseModel> add_challengePublic(@Header("Authorization") String Authorization,
                                            @Field("challenge_type") String challenge_type,
                                            @Field("title") String title,
                                            @Field("start_date") String start_date,
                                            @Field("end_date") String end_date,
                                            @Field("description") String description,
                                            @Field("favour") String favour,
                                            @Field("participant_list") String participant_list);

    @POST("add_challenge")
    @FormUrlEncoded
    Call<ResponseModel> add_challengePrivate(@Header("Authorization") String Authorization,
                                             @Field("challenge_type") String challenge_type,
                                             @Field("title") String title,
                                             @Field("start_date") String start_date,
                                             @Field("end_date") String end_date,
                                             @Field("description") String description,
                                             @Field("favour") String favour);





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

    @POST("add_feedback")
    @Multipart
    Call<ResponseModel> add_feedback(@Header("Authorization") String authorization,
                                     @Part("description") RequestBody description,
                                     @Part MultipartBody.Part img_1,
                                     @Part MultipartBody.Part img_2,
                                     @Part MultipartBody.Part img_3,
                                     @Part MultipartBody.Part img_4
    );


    @POST("add_group_chat")
    @Multipart
    Call<ResponseModel> add_group_chat(@Header("Authorization") String Authorization,
                                       @Part("title") RequestBody title,
                                       @Part("user_list") RequestBody user_list,
                                       @Part MultipartBody.Part file);

    @POST("update-profile")
    @Multipart
    Call<UpdateUserModel> update_profile(@Header("Authorization") String Authorization,
                                         @Part("first_name") RequestBody first_name,
                                         @Part("last_name") RequestBody last_name,
                                         @Part("address") RequestBody address,
                                         @Part("city") RequestBody city,
                                         @Part("state") RequestBody state,
                                         @Part("country") RequestBody country,
                                         @Part("phonenumber") RequestBody phonenumber,
                                         @Part("lat") RequestBody lat,
                                         @Part("lng") RequestBody lng,
                                         @Part("device_id") RequestBody device_id,
                                         @Part("device_type") RequestBody device_type,
                                         @Part MultipartBody.Part file);


}