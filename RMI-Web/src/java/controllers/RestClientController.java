/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import errors.rest.ExceptionXml;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:RmiApiResource [rmiapi]<br>
 * USAGE:
 * <pre>
 *        RestClientController client = new RestClientController();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author cynthia
 */
public class RestClientController {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/WebServiceRMI/rest";

    public RestClientController() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("rmiapi");
    }

    public <T> T getHelloWorld(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("hello");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
    }

    public <T> T getProcessList(Class<T> responseType, String p, String host) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getListOfProcesses{0}{1}", new Object[]{p, host}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
    }

    public <T> T getPhysicalMemory(Class<T> responseType, String p, String host) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getPhysicalMemory{0}{1}", new Object[]{p, host}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
    }

    public String getXml() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(String.class);
    }

    public void putXml(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public <T> T getCPU(Class<T> responseType, String p, String host) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getCPU{0}{1}", new Object[]{p, host}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
    }

    public <T> T getLastException(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("lastError");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
