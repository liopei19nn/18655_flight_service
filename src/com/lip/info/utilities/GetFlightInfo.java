package com.lip.info.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Li Pei
 *
 * Andrew ID : lip
 */
public class GetFlightInfo {

    // build a request to get flight service
    private static String buildRequest(String comCode, String number) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DATE);
        comCode.toUpperCase();
        StringBuilder s = new StringBuilder();
        s.append("https://api.flightstats.com/flex/flightstatus/rest/v2/json/flight/status/");
        s.append(comCode);
        s.append("/");
        s.append(number);
        s.append("/arr/");
        s.append(year);
        s.append("/");
        s.append(month);
        s.append("/");
        s.append(date);
        s.append("?appId=80213cc7&appKey=983b5df14eb0b3982e77d4ace157b1d1&utc=false");

        return s.toString();
    }

    // get connection to outside service
    private static String[] getFlightConnection(String comCode, String number) {

        String[] ret = null;
        String request = buildRequest(comCode, number);
        try {
            URL url = new URL(request);
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

    // get flight from outside server in string style
    public static String[] getFlight(String comCode, String number) {
        String[] raw = getFlightConnection(comCode, number);
        int errorIndex = 0;

        List<String> flightReport = new ArrayList<>();

        for (errorIndex = 0; errorIndex < raw.length; errorIndex++) {
            if (raw[errorIndex].contains("error")) {
                break;
            }
            if (raw[errorIndex].contains("airports")) {
                break;
            }
        }
        // if not found
        if (errorIndex == raw.length || raw[errorIndex].contains("error")) {
            flightReport.add("Cannot Find : " + comCode + number);
        } else {
            String arrivalCode = null;
            String arrivalDate = null;
            String depCode = null;
            for (int i = errorIndex; i < raw.length; i++) {
                if (raw[i].contains("departureAirportFsCode")) {
                    String[] parse = raw[i].split(":");
                    depCode = parse[1].substring(1, parse[1].length() - 1);
                }

                if (raw[i].contains("arrivalAirportFsCode") && arrivalCode == null) {
                    String[] parse = raw[i].split(":");
                    arrivalCode = parse[1].substring(1, parse[1].length() - 1);
                }

                if (raw[i].contains("arrivalDate") && arrivalDate == null) {
                    i++;
                    String[] parse = raw[i].split("[:.T]");
                    arrivalDate = parse[2] + ":" + parse[3] + ":" + parse[4];
                }

            }

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int date = cal.get(Calendar.DATE);

            flightReport.add("Departure From : " + depCode);
            flightReport.add("Arrive At : " + arrivalCode);
            flightReport.add("Arrival Local Time : " + year + "." + month + "." + date + ".  " + arrivalDate);

        }


        String[] ret = new String[flightReport.size()];
        flightReport.toArray(ret);

        return ret;

    }
}
