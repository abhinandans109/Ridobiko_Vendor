package com.ridobiko.ridobikoPartner.models;

import android.os.Parcelable;

import java.io.Serializable;

public class BookingResponseModel implements Serializable {

    public String id;
    public String bookedon;
    public String pickup_date;
    public String drop_date	 ;
    public String trans_id	 ;
    public String vendor_email_id	 ;
    public String customer_email_id;
    public String customer_name      ;
    public String customer_mobile	;
    public String customer_address	;
    public String customer_id_name	;
    public String customer_id_number;
    public String customer_dl_number;
    public String bikes_id			;
    public String status	;
    public String comment			;
    public String returned_bikes_id	;
    public String web	;
    public String rent;
    public String payment_mode		;
    public String late_apply	;
    public String amount_paid	;
    public String amount_left	;
    public String discount		;
    public String booking		;
    public String paid_on		;
    public String admin_status	;
    public String booking_from	;
    public String tenture	;
    public String user_status	;
    public String contact_name	;
    public String house_no		;
    public String landmark		;
    public String area			;
    public String latitude		;
    public String longitude		;
    public String delivery_location	;
    public String invoice;
    public Pictures pictures;
    public CustomerPictures customerPictures;
    public TripDetails trip_details;


   public String  login_mobile,
    whatsapp,
    name,
    email,
    email_verified,
    gender,
    current_address_house,
    current_address_area,
    current_address_landmark,
    current_address_city,
    permanent_address_house,
    permanent_address_area,
    permanent_address_landmark,
    permanent_address_city,
    employer,
    profession,
    emergency_no,
    emobile_verified,
    aadhar_id,
    driving_id,
    image_aadhar_front,
    image_aadhar_back,
    image_driving,
    image_office,
    image_profile,
    aadhar_verified,
    driving_license_verified,
    facebook,
    linkedin,
    created_on,
    image_pan,
    pan_verified,
    phone,
    Ridobiko_points,
    profilepic_verified,
    pan_no,
    bike_name,
    pickup,
    drop,
    bike_image,
           max_fuel_bars,
           km_limit,
           additional_km_cost,
           rent_per_day,
           fuel_tank,

           no_of_helmets,
           fuel_pickup,
           petrol_charge,
           KM_meter_pickup,

    security_deposit;

}
