package com.teamtreehouse.country.model;

import javax.persistence.*;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

    @Column
    private String name;

    @Column
    private float internetUsers;

    @Column
    private float adultLiteracyRat;

    //Default constructor for JPA
    public Country() {}

    public Country(CountryBuilder builder) {
        this.name = builder.name;
        this.internetUsers = builder.internetUsers;
        this.adultLiteracyRat = builder.adultLiteracyRat;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", internetUsers=" + internetUsers +
                ", adultLiteracyRat=" + adultLiteracyRat +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getInternetUsers() {
        return internetUsers;
    }

    public void setInternetUsers(float internetUsers) {
        this.internetUsers = internetUsers;
    }

    public float getAdultLiteracyRat() {
        return adultLiteracyRat;
    }

    public void setAdultLiteracyRat(float adultLiteracyRat) {
        this.adultLiteracyRat = adultLiteracyRat;
    }

    public static class CountryBuilder {

        private String name;
        private float internetUsers;
        private float adultLiteracyRat;

        public CountryBuilder(String name) {
            this.name = name;
        }

        public CountryBuilder withInternetUsers(float internetUsers) {
            this.internetUsers = internetUsers;
            return this;
        }

        public CountryBuilder withAdultLiteracyRat(float adultLiteracyRat) {
            this.adultLiteracyRat = adultLiteracyRat;
            return this;
        }

        public Country build() {
            return new Country(this);
        }

    }

}
