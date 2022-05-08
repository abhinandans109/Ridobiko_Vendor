package com.ridobiko.ridobikoPartner.api;

import com.ridobiko.ridobikoPartner.models.ApiResponseModel;
import com.ridobiko.ridobikoPartner.models.BikesResponseModel;
import com.ridobiko.ridobikoPartner.models.BookingResponseModel;
import com.ridobiko.ridobikoPartner.models.LoginUserResponse;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    //modifications

//    @GET("verify-code")
//    Call<ApiResponseModel<ValidateInviteResponse>> validateInviteCode(
//            @Query("invite_code") String invite_code
//    );

    @FormUrlEncoded
    @POST("login_vendor.php")
    Call<ApiResponseModel<LoginUserResponse>> login_vendor(
            @Field("email") String email,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("getTodaysPickups.php")
    Call<ApiResponseModel<ArrayList<BookingResponseModel>>> getTodaysPickups(
            @Field("email") String email
    );
    @FormUrlEncoded
    @POST("BikeidFetchOnPickup.php")
    Call<ApiResponseModel<ArrayList<BikesResponseModel>>> getAvailableBikes(
            @Field("email") String email
    );
    @FormUrlEncoded
    @POST("imageUpload.php")
    Call<ApiResponseModel<String>> uploadPickupImages(
            @Field("orderId") String orderId,
            @Field("bikeId") String bikeId,
            @Field("customerAdhaarFront") String customerAdhaarFront,
            @Field("customerAdhaarBack") String customerAdhaarBack,
            @Field("customerDriving") String customerDriving,
            @Field("customerOfficeId") String customerOfficeId,
            @Field("bikeLeft") String bikeLeft,
            @Field("bikeRight") String bikeRight,
            @Field("bikeFront") String bikeFront,
            @Field("bikeBack") String bikeBack,
            @Field("bikeCustomer") String bikeCustomer,
            @Field("bikeFuel") String bikeFuel,
            @Field("helmet_front_1") String helmet_front_1,
            @Field("helmet_back_1") String helmet_back_1,
            @Field("helmet_front_2") String helmet_front_2,
            @Field("helmet_back_2") String helmet_back_2
    );
    @FormUrlEncoded
    @POST("insertPickup.php")
    Call<ApiResponseModel<String>> submitPickup(
            @Field("order_id") String order_id,
            @Field("drop_date") String drop_date,
            @Field("bike_id") String bike_id,
            @Field("bookedon") String bookedon,
            @Field("status") String status,
            @Field("rent_collected") String rent_collected,
            @Field("mode_of_rent") String mode_of_rent,
            @Field("deposit") String deposit,
            @Field("mode_of_deposit") String mode_of_deposit,
            @Field("no_of_helmets") String no_of_helmets,
            @Field("KM_meter_pickup") String KM_meter_pickup,
            @Field("fuel_pickup") String fuel_pickup,
            @Field("destination") String destination,
            @Field("purpose") String purpose,
            @Field("idCollected") String idCollected,
            @Field("change_vid") String change_vid,
            @Field("change_vehicle_id") String change_vehicle_id,
            @Field("city") String city
    );

}

