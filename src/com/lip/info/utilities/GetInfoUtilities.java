package com.lip.info.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Li Pei
 *
 * Andrew ID : lip
 */
public class GetInfoUtilities {
    public static String[] getAllInfo(String comCode, String number) {
        String[] flightInfo = GetFlightInfo.getFlight(comCode, number);
        String[] weatherInfo  = null;
        List<String> allInfo = new ArrayList<>();


        // if flight info length == 1, you get error info from outside service
        // like flight not found
        if (flightInfo.length == 1) {
            allInfo.add(flightInfo[0]);
        }else{
            int breakIndex = flightInfo[1].indexOf(':');
            String arrAirportCode = flightInfo[1].substring(breakIndex + 2);
            weatherInfo = GetWeahterInfo.getWeather(arrAirportCode);

            for (int i = 0; i < flightInfo.length; i++) {
                allInfo.add(flightInfo[i]);
            }

            for (int i = 0; i < weatherInfo.length; i++) {
                allInfo.add(weatherInfo[i]);
            }

        }

        String[] ret = new String[allInfo.size()];
        allInfo.toArray(ret);
        return ret;

    }
}
