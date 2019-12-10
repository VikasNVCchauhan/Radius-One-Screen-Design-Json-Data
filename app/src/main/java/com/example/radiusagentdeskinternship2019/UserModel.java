package com.example.radiusagentdeskinternship2019;

public class UserModel {

    private String stringStartingAddress, stringDestinationAddress, stringStartingDate, stringDestinationDate, stringTravelTime, stringTotalRevenue, stringCurrencySymbol;

    public UserModel(String stringStartingAddress, String stringDestinationAddress, String stringStartingDate, String stringDestinationDate, String stringTravelTime, String stringTotalRevenue, String stringCurrencySymbol) {
        this.stringStartingAddress = stringStartingAddress;
        this.stringDestinationAddress = stringDestinationAddress;
        this.stringStartingDate = stringStartingDate;
        this.stringDestinationDate = stringDestinationDate;
        this.stringTravelTime = stringTravelTime;
        this.stringTotalRevenue = stringTotalRevenue;
        this.stringCurrencySymbol = stringCurrencySymbol;
    }

    public String getStringStartingAddress() {
        return stringStartingAddress;
    }

    public String getStringDestinationAddress() {
        return stringDestinationAddress;
    }

    public String getStringStartingDate() {
        return stringStartingDate;
    }

    public String getStringDestinationDate() {
        return stringDestinationDate;
    }

    public String getStringTravelTime() {
        return stringTravelTime;
    }

    public String getStringTotalRevenue() {
        return stringTotalRevenue;
    }

    public void setStringStartingAddress(String stringStartingAddress) {
        this.stringStartingAddress = stringStartingAddress;
    }

    public void setStringDestinationAddress(String stringDestinationAddress) {
        this.stringDestinationAddress = stringDestinationAddress;
    }

    public void setStringStartingDate(String stringStartingDate) {
        this.stringStartingDate = stringStartingDate;
    }

    public void setStringDestinationDate(String stringDestinationDate) {
        this.stringDestinationDate = stringDestinationDate;
    }

    public void setStringTravelTime(String stringTravelTime) {
        this.stringTravelTime = stringTravelTime;
    }

    public void setStringTotalRevenue(String stringTotalRevenue) {
        this.stringTotalRevenue = stringTotalRevenue;
    }

    public String getStringCurrencySymbol() {
        return stringCurrencySymbol;
    }

    public void setStringCurrencySymbol(String stringCurrencySymbol) {
        this.stringCurrencySymbol = stringCurrencySymbol;
    }

    public UserModel() {
    }
}
