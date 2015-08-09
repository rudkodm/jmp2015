package com.rm.eholiday.beans;

public enum PriceUnit {

    TOTAL,
    PERSON;

    public int getId() {
        return ordinal();
    }

    public static PriceUnit priceUnitById(int id) {
        return values()[id];
    }

}
