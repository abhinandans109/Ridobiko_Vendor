package com.ridobiko.ridobikoPartner.models;

import java.util.ArrayList;

public class BikeBrandNameResponseModel {
    public String bike_brand;
    public ArrayList<BikeName> bikes;

    public class BikeName{
        public String bike_name;
    }
}
