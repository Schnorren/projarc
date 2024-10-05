package com.projarc.sarc.domain.model;

public enum HorarioEnum {
    A("08:00-08:45"),
    B("08:45-09:30"),
    C("09:45-10:30"),
    D("10:30-11:15"),
    E("11:30-12:15"),
    E1("12:15-13:00"),
    F("14:00-14:45"),
    G("14:45-15:30"),
    H("15:45-16:30"),
    I("16:30-17:15"),
    J("17:30-18:15"),
    K("18:15-19:00"),
    L("19:15-20:00"),
    M("20:00-20:45"),
    N("21:00-21:45"),
    P("21:45-22:30");

    private String timeRange;

    HorarioEnum(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getTimeRange() {
        return timeRange;
    }
}
