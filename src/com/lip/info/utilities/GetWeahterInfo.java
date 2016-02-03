package com.lip.info.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Li Pei
 *
 * Andrew ID : lip
 */
public class GetWeahterInfo {
    private static String[] getWeatherConnection(String airportCode) {
        String[] ret = null;

        try {
            URL url = new URL("https://api.flightstats.com/flex/weather/rest/v1/json/metar/" + airportCode
                    + "?appId=80213cc7&appKey=983b5df14eb0b3982e77d4ace157b1d1");
            URLConnection connection = url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            for (String currLine = null; (currLine = br.readLine()) != null;) {
                ret = currLine.split("[{},]");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ret = null;
        }

        return ret;

    }

    // get weather information with airport code
    public static String[] getWeather(String airportCode) {
        String[] raw = getWeatherConnection(airportCode);

        List<String> weatherReport = new ArrayList<>();

        for (int i = 0; i < raw.length; i++) {
            if (raw[i].contains("error")) {
                weatherReport.add("Wrong Airport Code : " + airportCode);
                break;
            }

            if (raw[i].contains("Prevailing Conditions")) {
                i++;
                String[] parse = raw[i].split(":");
                weatherReport.add("Prevailing Conditions : " + parse[1].substring(1, parse[1].length() - 1));
            }

            if (raw[i].contains("temperatureCelsius")) {
                String[] parse = raw[i].split(":");
                weatherReport.add("Celsius Temperature : " + parse[1].substring(1, parse[1].length() - 1));
            }

            if (raw[i].contains("dewPointCelsius")) {
                String[] parse = raw[i].split(":");
                weatherReport.add("Dew Point Celsius : " + parse[1].substring(1, parse[1].length() - 1));
            }

        }

        // prevailing code may be not available
        if (weatherReport.size() != 3) {
            weatherReport.add(0, "Prevailing Conditions : Not Available");
        }

        String[] ret = new String[weatherReport.size()];
        weatherReport.toArray(ret);
        return ret;
    }
}
