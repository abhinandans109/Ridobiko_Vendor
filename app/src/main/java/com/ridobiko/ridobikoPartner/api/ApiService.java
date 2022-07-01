package com.ridobiko.ridobikoPartner.api;

import com.ridobiko.ridobikoPartner.models.ApiResponseModel;
import com.ridobiko.ridobikoPartner.models.BikeBrandNameResponseModel;
import com.ridobiko.ridobikoPartner.models.BikesResponseModel;
import com.ridobiko.ridobikoPartner.models.BookingResponseModel;
import com.ridobiko.ridobikoPartner.models.ChangeStatusResponseModel;
import com.ridobiko.ridobikoPartner.models.CustomerDetailsResponseModel;
import com.ridobiko.ridobikoPartner.models.CustomerHistoryResponseModel;
import com.ridobiko.ridobikoPartner.models.FuelPriceAndNumbers;
import com.ridobiko.ridobikoPartner.models.LoginUserResponse;
import com.ridobiko.ridobikoPartner.models.MyBikesResponseModel;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
    @POST("changeAdminStatus.php")
    Call<ChangeStatusResponseModel> changeAdminStatus(
            @Field("order_id") String order_id,
            @Field("status") String status
    );
    @FormUrlEncoded
    @POST("getTodaysDrops.php")
    Call<ApiResponseModel<ArrayList<BookingResponseModel>>> getTodaysDrops(
            @Field("email") String email
    );
    @FormUrlEncoded
    @POST("getMyBikes.php")
    Call<ApiResponseModel<ArrayList<MyBikesResponseModel>>> getMyBikes(
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
    @POST("getNumberAndFuel.php")
    Call<ApiResponseModel<FuelPriceAndNumbers>> getNumberAndFuel(
            @Field("email") String email
    );
    @FormUrlEncoded
    @POST("rent.php")
    Call<ApiResponseModel<Double>> getrent(
            @Field("email") String email,
            @Field("bikeid") String bikeid,
            @Field("pdate") String pdate,
            @Field("ddate") String ddate,
            @Field("ptime") String ptime,
            @Field("dtime") String dtime
    );
    @FormUrlEncoded
    @POST("imageUpload.php")
    Call<ResponseBody> uploadPickupImages(
            @Field("orderId") String orderId,
            @Field("bikeId") String bikeId,
            @Field("mob") String mob,
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
    @POST("kmLimit.php")
    Call<ApiResponseModel<String>> calculateKmCharges(
            @Field("email") String email,
            @Field("bid") String bid,
            @Field("pickup_date") String pickup_date,
            @Field("drop_date") String drop_date
    );
    @FormUrlEncoded
    @POST("exchangeBike.php")
    Call<ApiResponseModel<String>> submitExchange(
            @Field("orderId") String orderId,
            @Field("bikeId") String bikeId,
            @Field("drop_date") String drop_date,
            @Field("bikeLeft") String bikeLeft,
            @Field("bikeRight") String bikeRight,
            @Field("bikeFront") String bikeFront,
            @Field("bikeBack") String bikeBack,
            @Field("bikeCustomer") String bikeCustomer,
            @Field("bikeFuel") String bikeFuel
    );

    @FormUrlEncoded
    @POST("insertPickup.php")
    Call<ApiResponseModel<String>> submitPickup(
            @Field("order_id") String order_id,
            @Field("bikeid") String bikeid,
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
            @Field("city") String city,
            @Field("deposit_collected_by_vendor") String deposit_collected_by_vendor
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

    @FormUrlEncoded
    @POST("ExtendBooking.php")
    Call<ApiResponseModel<String>> extendBooking(
            @Field("bid") String bid,
            @Field("order_id") String order_id,
            @Field("Rent") String Rent,
            @Field("pick_date") String pick_date,
            @Field("drop_date") String drop_date,
            @Field("pick_time") String pick_time,
            @Field("drop_time") String drop_time,
            @Field("deposit_collected") String deposit_collected,
            @Field("deposit_payment_mode") String deposit_payment_mode,
            @Field("no_of_helmets") String no_of_helmets,
            @Field("KM_meter_pickup") String KM_meter_pickup,
            @Field("fuel_pickup") String fuel_pickup,
            @Field("destination") String destination,
            @Field("purpose") String purpose,
            @Field("id_collected") String id_collected,
            @Field("city") String city
    );
    @FormUrlEncoded
    @POST("set_addBikes.php")
    Call<ChangeStatusResponseModel> addBike(
            @Field("brand") String brand,
            @Field("bikename") String bikename,
            @Field("plate") String plate,
            @Field("modal_year") String modal_year,
            @Field("plate_number") String plate_number,
            @Field("weekday") String weekday,
            @Field("Weekend") String Weekend,
            @Field("renthour") String renthour,
            @Field("speed") String speed,
            @Field("km_day") String km_day,
            @Field("km_month") String km_month,
            @Field("charge") String charge,
            @Field("deposite") String deposite,
            @Field("vemail") String vemail
    );

    @FormUrlEncoded
    @POST("set_bank.php")
    Call<ChangeStatusResponseModel> setBank(
            @Field("email") String email,
            @Field("accno") String accno,
            @Field("ifsc") String ifsc,
            @Field("accname") String accname
            );

    @FormUrlEncoded
    @POST("set_Documents.php")
    Call<ChangeStatusResponseModel> documents(
            @Field("email") String email,
            @Field("dl") String dl,
            @Field("aadhar") String aadhar,
            @Field("pan") String pan
    );
    @FormUrlEncoded
    @POST("set_homedelivery.php")
    Call<ChangeStatusResponseModel> homeDelivery(
            @Field("email") String email,
            @Field("homedel") String homedel,
            @Field("lessthen3km") String lessthen3km,
            @Field("threeto5km") String threeto5km,
            @Field("fiveto8km") String fiveto8km,
            @Field("eightto10km") String eightto10km,
            @Field("morethan10km") String morethan10km
    );

    @FormUrlEncoded
    @POST("set_addrress.php")
    Call<ChangeStatusResponseModel> setAddress(
            @Field("email") String email,
            @Field("landmark") String landmark,
            @Field("area") String area,
            @Field("city") String city,
            @Field("pincode") String pincode,
            @Field("state") String state
    );

    @FormUrlEncoded
    @POST("set_store.php")
    Call<ChangeStatusResponseModel> setStore(
            @Field("email") String email,
            @Field("oname") String oname,
            @Field("ono") String ono,
            @Field("ename") String ename,
            @Field("eno") String eno,
            @Field("cname") String cname,
            @Field("map") String map
    );

    @FormUrlEncoded
    @POST("set_pillion.php")
    Call<ChangeStatusResponseModel> setPillion(
            @Field("email") String email,
            @Field("partbooksel") String partbooksel,
            @Field("rent") String rent,
            @Field("helmentadditional") String helmentadditional,
            @Field("helmentcharge") String helmentcharge

    );



    @FormUrlEncoded
    @POST("set_PickupDropdate.php")
    Call<ChangeStatusResponseModel> setPickupDropdate(
            @Field("till") String till,
            @Field("from") String from,
            @Field("bikeid") String bikeid

    );

    @FormUrlEncoded
    @POST("set_fuelMeter.php")
    Call<ChangeStatusResponseModel> setFuelMeter(
            @Field("fuelTrank") String fuelTrank,
            @Field("fuelbars") String fuelbars,
            @Field("bikeid") String bikeid

    );

    @FormUrlEncoded
    @POST("set_statusHsrp.php")
    Call<ChangeStatusResponseModel> setStatusHsrp(
            @Field("rentType") String rentType,
            @Field("status") String status,
            @Field("bikeid") String bikeid

    );
    @FormUrlEncoded
    @POST("set_discountSubscription.php")
    Call<ChangeStatusResponseModel> setDiscountSubcription(
            @Field("bikeid") String bikeid,
            @Field("d0") String d0,
            @Field("d7") String d7,
            @Field("d11") String d11,
            @Field("d15") String d15,
            @Field("d21") String d21,
            @Field("d30") String d30,
            @Field("d30") String s1,
            @Field("d30") String s2,
            @Field("d30") String s3,
            @Field("d30") String s6,
            @Field("d30") String s12

    );
    @FormUrlEncoded
    @POST("set_t&c.php")
    Call<ChangeStatusResponseModel> setTandC(
            @Field("tc") String tc,
            @Field("bikeid") String bikeid

    );

    @FormUrlEncoded
    @POST("set_MyBikesVehicleDetails.php")
    Call<ChangeStatusResponseModel> setMyBikesVehicleDetails(
            @Field("bikeid") String bikeid,
            @Field("plateNo") String plateNo,
            @Field("brand") String brand,
            @Field("bikeName") String bikeName,
            @Field("plateType") String plateType,
            @Field("weekday") String weekday,
            @Field("weekend") String weekend,
            @Field("deposit") String deposit,
            @Field("hour") String hour,
            @Field("kmDay") String kmDay,
            @Field("kmMonth") String kmMonth,
            @Field("addCost") String addCost,
            @Field("speedLimit") String speedLimit,
            @Field("modelYear") String modelYear,
            @Field("bikeAdded") String bikeAdded,
            @Field("insurance_ex") String insurance_ex,
            @Field("puc_expiry") String puc_expiry,
            @Field("permit_expiry") String permit_expiry,
            @Field("fitness_expiry") String fitness_expiry

    );
    @FormUrlEncoded
    @POST("get_AddBikes.php")
    Call<ApiResponseModel<ArrayList<BikeBrandNameResponseModel>>> getAddBikes(
            @Field("email") String email
            );

    @FormUrlEncoded
    @POST("get_custdetails.php")
    Call<ApiResponseModel<ArrayList<CustomerDetailsResponseModel>>> getCustDetails(
            @Field("vendor_email") String vendor_email
    );
    @FormUrlEncoded
    @POST("get_bookinghistory.php")
    Call<ApiResponseModel<ArrayList<CustomerHistoryResponseModel>>> getBookingHistory(
            @Field("mobile") String mobile
            );


}

