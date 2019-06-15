package com.example.apple.halanx_test_app;

public class Listitem {

    String name;
    String city;
    String state;
    String cover_pic_url;
    String rent_from;
    String security_deposit_from;
    String accomodation_allowed_str;
    String available_bed_count;

    public Listitem(String name,String city,String state,String pic,String rent,String deposite,String accomd, String bed){
        this.name=name;
        this.city=city;
        this.state=state;
        this.cover_pic_url=pic;
        this.rent_from=rent;
        this.security_deposit_from=deposite;
        this.accomodation_allowed_str=accomd;
        this.available_bed_count=bed;
    }

    public String getRent_from() {
        return rent_from;
    }

    public String getAvailable_bed_count() {
        return available_bed_count;
    }

    public String getSecurity_deposit_from() {
        return security_deposit_from;
    }

    public String getAccomodation_allowed_str() {
        return accomodation_allowed_str;
    }

    public String getCity() {
        return city;
    }

    public String getCover_pic_url() {
        return cover_pic_url;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }
}
