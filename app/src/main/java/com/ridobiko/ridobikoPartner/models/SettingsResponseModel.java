package com.ridobiko.ridobikoPartner.models;

public class SettingsResponseModel {
   public Home_Delivery Home_Delivery;
    public Pillion Pillion;
    public  Bank Bank;
    public Documents Documents;
    public store_details store_details;
    public Address Address;
    public Store_opening_closing Store_opening_closing;

   public class Home_Delivery{
       public String home_delivery;
       public String less_than_three_km;
       public String three_to_five_km;
       public String five_to_eight_km;
       public String eight_to_ten_km;
       public String more_than_ten_km;
   }
   public class Pillion{
       public String remaining_payment_flag;
       public String rent_type;
       public String Helmet_additional;
       public String Helmet_charge;
       public String weekly_rental;
   }
   public class Store_opening_closing{
       public String map_address;
       public String courses;
       public String form_opening;
       public String form_day;
       public String form_closing;
       public String closeing_day;
   }
   public class Address{
       public String form_area;
       public String form_city;
       public String form_landmark;
       public String form_pin;
       public String form_state;
   }
   public class Documents{
       public String lc_image;
       public String form_pan;
       public String adhar_image;
       public String pan_image;
       public String form_lc;
   }
   public class Bank{
       public String account_holder;
       public String account_no;
       public String ifsc;
   }
   public class store_details{
       public String map_address;
       public String employee_name;
       public String employee_number;
       public String company_name;
       public String company_phone;
   }

}
