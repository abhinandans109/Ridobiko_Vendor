package com.ridobiko.ridobikoPartner.api;

import com.ridobiko.ridobikoPartner.AppVendor;

public class API {
    public static ApiService get() {
        return AppVendor.buildApiService();
    }
}
