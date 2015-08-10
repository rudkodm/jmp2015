package com.rm.eholiday.beans;

public enum Category {

    AGROTOURISM,
    APARTMENT,
    COTTAGE_HOUSE,
    CAMPING_HOUSE,
    HOSTEL,
    HOTEL,
    CAMPING,
    PROPERTY,
    MOTEL,
    RESORT,
    GUESTHOUSE,
    SANITARIUM,
    YOUTH_HOSTEL,
    SPA,
    MARINA,
    FISHING_STATION,
    SLEEPING,
    VILLA,
    INN,
    CASTLE_MANOR_PALACE,
    FLAT_FOR_RENT,
    HOUSE_FOR_RENT,
    ACADEMICIAN,
    BOARDING_HOUSE,
    DORMITORY,
    GUEST_ROOMS,
    STAFF_ACCOMMODATION,
    APARTHOTEL,
    VACATION_CENTRE,
    TRAINING_CENTRE,
    BED_AND_BREAKFAST;

    public int getId() {
        return ordinal() + 1;
    }

    public static Category categoryById(int id) {
        return values()[--id];
    }

}
