package com.utils;

import com.model.TransportationInfo;

import java.util.Comparator;

public class TimeComparator implements Comparator<TransportationInfo> {

    @Override
    public int compare(TransportationInfo x, TransportationInfo y)
    {
        return Double.compare(x.getTime(),y.getTime());
    }
}
