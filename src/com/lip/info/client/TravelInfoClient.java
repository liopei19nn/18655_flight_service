package com.lip.info.client;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * @author Li Pei
 *
 * Andrew ID : lip
 */

public class TravelInfoClient {

    private static final String BASE_URI = "http://localhost:8080/lip_18655_homework4";
    private static final String PATH_NAME = "/TravelInfoService/comCode/%s/number/%s";

    /**
     * @param comcode
     *            input airline company code
     * @param number
     *            input airline code
     * @return a XML string with weather and flight information
     */
    public static String getAllInfoResponse(String comCode, String number) {

        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource resource = client.resource(BASE_URI);

        String totalPath = String.format(PATH_NAME, comCode, number);

        // access resource
        WebResource nameResource = resource.path("rest").path(totalPath);

        // get client status response
        String clientRespons = getClientResponse(nameResource);

        // if success
        if (clientRespons.endsWith("200 OK")) {
            return getResponse(nameResource);
        } else {
            return "Error : " + clientRespons;
        }
    }

    /**
     * Returns client response. e.g : GET
     * http://localhost:8080/lip_18644_homework4/rest/TravelInfoService/comCode/
     * AA/number/100 returned a response status of 200 OK
     *
     * @param service
     * @return
     */
    private static String getClientResponse(WebResource resource) {
        return resource.accept(MediaType.TEXT_XML).get(ClientResponse.class).toString();
    }

    /**
     * Returns the response as XML e.g : <User><Name>Pavithra</Name></User>
     *
     * @param service
     * @return
     */
    private static String getResponse(WebResource resource) {
        return resource.accept(MediaType.TEXT_XML).get(String.class);
    }
}
