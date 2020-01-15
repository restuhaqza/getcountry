package dev.restuhaqza.country.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class CountryModel implements Parcelable {
    private String name;
    private String alpha2code;
    private String alpha3code;
    private String nativeName;
    private String region;
    private String subRegion;
    private String latitude;
    private String longitude;
    private Double area;
    private Integer numericCode;
    private String nativeLang;
    private String currencyCode;
    private String currencyName;


    public CountryModel(){

    }

    public CountryModel(Parcel in) {
        name = in.readString();
        alpha2code = in.readString();
        alpha3code = in.readString();
        nativeName = in.readString();
        region = in.readString();
        subRegion = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        if (in.readByte() == 0) {
            area = null;
        } else {
            area = in.readDouble();
        }
        if (in.readByte() == 0) {
            numericCode = null;
        } else {
            numericCode = in.readInt();
        }
        nativeLang = in.readString();
        currencyCode = in.readString();
        currencyName = in.readString();
        currencySymbol = in.readString();
        flag = in.readString();
        flagPng = in.readString();
    }

    public static final Creator<CountryModel> CREATOR = new Creator<CountryModel>() {
        @Override
        public CountryModel createFromParcel(Parcel in) {
            return new CountryModel(in);
        }

        @Override
        public CountryModel[] newArray(int size) {
            return new CountryModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2code() {
        return alpha2code;
    }

    public void setAlpha2code(String alpha2code) {
        this.alpha2code = alpha2code;
    }

    public String getAlpha3code() {
        return alpha3code;
    }

    public void setAlpha3code(String alpha3code) {
        this.alpha3code = alpha3code;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        if(area == null) this.area = 0.0;
        else this.area = area;
    }

    public Integer getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(Integer numericCode) {
        this.numericCode = numericCode;
    }

    public String getNativeLang() {
        return nativeLang;
    }

    public void setNativeLang(String nativeLang) {
        this.nativeLang = nativeLang;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlagPng() {
        return flagPng;
    }

    public void setFlagPng(String flagPng) {
        this.flagPng = flagPng;
    }

    private String currencySymbol;
    private String flag;
    private String flagPng;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(alpha2code);
        dest.writeString(alpha3code);
        dest.writeString(nativeName);
        dest.writeString(region);
        dest.writeString(subRegion);
        dest.writeString(latitude);
        dest.writeString(longitude);
        if (area == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 2);
            dest.writeDouble(area);
        }
        if (numericCode == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 2);
            dest.writeInt(numericCode);
        }
        dest.writeString(nativeLang);
        dest.writeString(currencyCode);
        dest.writeString(currencyName);
        dest.writeString(currencySymbol);
        dest.writeString(flag);
        dest.writeString(flagPng);
    }
}
