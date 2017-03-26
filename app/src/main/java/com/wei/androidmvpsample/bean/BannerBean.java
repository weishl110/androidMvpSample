package com.wei.androidmvpsample.bean;

/**
 * Created by ${wei} on 2017/3/26.
 */

public class BannerBean {
    //"activeUrl": "http://www.baidu.com",
//      "bannerId": "20170110151715",
//              "bannerImgUrl": "http://10.0.150.26:7070/xire_store/XIRE_FILES/XiReApp/banner/1484032916120.jpg",
//              "bannerName": "炒股大赛",
//              "date": "2017-01-10 00:00:00"

    private String activeUrl;
    private String bannerId;
    private String bannerimgUrl;
    private String bannerName;
    private String date;

    public String getActiveUrl() {
        return activeUrl;
    }

    public void setActiveUrl(String activeUrl) {
        this.activeUrl = activeUrl;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerimgUrl() {
        return bannerimgUrl;
    }

    public void setBannerimgUrl(String bannerimgUrl) {
        this.bannerimgUrl = bannerimgUrl;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BannerBean{" +
                "activeUrl='" + activeUrl + '\'' +
                ", bannerId='" + bannerId + '\'' +
                ", bannerimgUrl='" + bannerimgUrl + '\'' +
                ", bannerName='" + bannerName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
