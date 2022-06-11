package com.ridobiko.ridobikoPartner.models;

import java.io.Serializable;

public class TripDetails implements Serializable {
    public String id, order_id, pickup_date, drop_date, booking_date, booking_status, city, rent_collected_by_vendor
            , rent_payment_mode, deposit_collected_by_vendor, deposit_payment_mode, no_of_bikes, no_of_helmets, no_of_helmets_drop, KM_meter_pickup, fuel_pickup,
            destination, purpose, id_collected, comment, KM_meter_drop, fuel_drop, petrol_charge, extra_km_charge="0", other_charge,
            other_charge_reason, vehicle_check, id_returned, collected_by, fuel_charge_apply, km_charge_apply, maintenance_charge_apply,
            charges_confirmed;
}
