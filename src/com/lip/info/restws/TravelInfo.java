package com.lip.info.restws;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lip.info.utilities.GetInfoUtilities;

/**
 * @author Li Pei
 *
 * Andrew ID : lip
 */

@Path("TravelInfoService")
public class TravelInfo {
    /**
     * service cache, if the key is in map, you do not have to access outside
     * service
     *
     */
    private static Map<String, String> cache = new HashMap<>();

    // @GET here defines, this method will method will process HTTP GET
    // requests.
    @GET
    // @Path here defines method level path. Identifies the URI path that a
    // resource class method will serve requests for.
    @Path("/comCode/{i}/number/{j}")
    // @Produces here defines the media type(s) that the methods
    // of a resource class can produce.
    @Produces(MediaType.TEXT_XML)
    // @PathParam injects the value of URI parameter that defined in @Path
    // expression, into the method.
    public String travelInfo(@PathParam("i") String comCode, @PathParam("j") String number) {

        // get flight and weather service
        String[] allInfo = GetInfoUtilities.getAllInfo(comCode, number);

        // if the request flight is not available
        if (allInfo.length == 1) {
            String ret = String.format("<Info>%s</Info>", "Cannot Find This Flight");
            return ret;
        }

        // get the key and check if it is in cache
        String key = comCode + number;
        String ret = null;
        if (cache.containsKey(key)) {
            // if in cache, just return content from cache
            ret = new String(cache.get(key));
        } else {
            // if not in cache, build a new value string
            ret = String.format(
                    "<Info>" + "<FlightReport>" + "<DepatureCode>%s</DepatureCode>" + "<ArrivalCode>%s</ArrivalCode>"
                            + "<ArrivalTime>%s</ArrivalTime>" + "</FlightReport>" + "<WeatherReport>"
                            + "<PrevailingConditions>%s</PrevailingConditions>" + "<Temperature>%s</Temperature>"
                            + "<DewPoint>%s</DewPoint>" + "</WeatherReport>" + "</Info>",
                    allInfo[0], allInfo[1], allInfo[2], allInfo[3], allInfo[4], allInfo[5]);

            // keep the cache size below 100
            if (cache.size() > 100) {
                for (String search : cache.keySet()) {
                    if (cache.size() < 100) {
                        break;
                    } else {
                        cache.remove(search);
                    }
                }
            }

            cache.put(key, ret);
        }
        return new String(ret);
    }
}
