package com.ridobiko.ridobikoPartner.models;

import java.io.Serializable;
import java.util.ArrayList;

public class MyBikesResponseModel implements Serializable {
   
        public String id;
        public String vendor_email_id;
        public String bike_id;
        public String bike_brand;
        public String bike_name;
        public String bike_plate_type;
        public String bike_image;
        public String actual_rent;
        public String rent_per_day;
        public String rent_per_day_weekend;
        public String rent_per_hour;
        public String insurance_renewal_date;
        public String periodic_service_date;
        public String service_renewal_month;
        public String status;
        public String pickup;
        public String drop;
        public String comment;
        public String tc;
        public String start;
        public String end;
        public String conditions;
        public String deposit;
        public String km_limit;
        public String Km_limit_month;
        public String additional_km_cost;
        public String speed_limit;
        public String fuel_tank;
        public String max_fuel_bars;
        public String rent_per_day_0_6;
        public String rent_per_day_7_10;
        public String rent_per_day_11_15;
        public String rent_per_day_16_20;
        public String rent_per_day_20_30;
        public String rent_per_day_30_100;
        public String file;
        public String engineNo;
        public String chassisNo;
        public String modelYear;
        public String RC;
        public String Insurance;
        public String PUC;
        public String Purchase_Bill;
        public String Permit;
        public String permit_b;
        public String Tax_Receipt;
        public String fimg;
        public String bimg;
        public String limg;
        public String rimg;
        public String timg;
        public String fuelimg;
        public String permit_expiry_date;
        public String taxreceipt_expiry_date;
        public String rc_expiry_date;
        public String puc_expiry_date;
        public String purchasebill_expiry_date;
        public String insurance_expiry_date;
        public String fitness_renewal_date;
        public String Sub_1month;
        public String Sub_2month;
        public String Sub_3month;
        public String Sub_6month;
        public String Sub_12month;
        public String hsrp_status;
        public String hsrp_apt_date;
        public String bike_added_on;
        public ArrayList < Maintainance > Maintenance;
         public dashboard Dashboard ;
         public  ArrayList < Trip_History > Trip_History ;

         public class Maintainance{

         }
         public class dashboard{
             public String bookings;
             public String amount;
             public String difference;
             public String Total_Maintenance_Cost;
             public String customer_name_count;
         }
         public class Trip_History{
             public String pickup_date;
             public String drop_date;
             public String customer_name;
             public String customer_mobile;
             public String amount_paid;
             public String amount_left;
             public String admin_status;
             public String rent;
         }
}
