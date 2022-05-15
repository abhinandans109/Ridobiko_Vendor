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
    @POST("getTodaysDrops.php")
    Call<ApiResponseModel<ArrayList<BookingResponseModel>>> getTodaysDrops(
            @Field("email") String email
    );
    @FormUrlEncoded
    @POST("getUpcommingsBookings.php")
    Call<ApiResponseModel<ArrayList<BookingResponseModel>>> getUpcommingBookings(
            @Field("email") String email
    );
    @FormUrlEncoded
    @POST("getBookingsHistory.php")
    Call<ApiResponseModel<ArrayList<BookingResponseModel>>> getAllBookings(
            @Field("email") String email
    );
    @FormUrlEncoded
    @POST("BikeidFetchOnPickup.php")
    Call<ApiResponseModel<ArrayList<BikesResponseModel>>> getAvailableBikes(
            @Field("email") String email
    );
    @FormUrlEncoded
    @POST("imageUpload.php")
    Call<ResponseBody> uploadPickupImages(
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
    @POST("imageUploadDrop.php")
    Call<ApiResponseModel<String>> uploadDropImages(
            @Field("orderId") String orderId,
            @Field("bikeId") String bikeId,
            @Field("bikeLeft") String bikeLeft,
            @Field("bikeRight") String bikeRight,
            @Field("bikeFront") String bikeFront,
            @Field("bikeBack") String bikeBack,
            @Field("bikeFuel") String bikeFuel
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

    @FormUrlEncoded
    @POST("insertDrop.php")
    Call<ApiResponseModel<String>> submitDrop(
            @Field("order_id") String order_id,
            @Field("drop_date") String drop_date,
            @Field("no_of_helemets_drop") String no_of_helemets_drop,
            @Field("comment") String comment,
            @Field("km_drop") String km_drop,
            @Field("fuel_drop") String fuel_drop,
            @Field("fuel_charges") String fuel_charges,
            @Field("extra_km_charge") String extra_km_charge,
            @Field("main_cost") String main_cost,
            @Field("main_comment") String main_comment,
            @Field("vehicle_condition") String vehicle_condition,
            @Field("original_id") String original_id,
            @Field("collected_by") String collected_by,
            @Field("fuel_charges_apply") String fuel_charges_apply,
            @Field("km_charges_apply") String km_charges_apply,
            @Field("maintenance_charge_apply") String maintenance_charge_apply,
            @Field("charges_confirmed") String charges_confirmed
    );

}

