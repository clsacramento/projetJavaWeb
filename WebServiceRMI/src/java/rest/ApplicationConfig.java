/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * RESTful web service from patterns
 * Generated RESTful root resource class with GET and PUT methods using Java API for RESTful Web Service (JSR-311). 
 * This pattern is useful for creating a simple HelloWorld service and wrapper services for invoking WSDL-based web services. 
 * On the next page you will be specifying class name, URI, and representation type of the resource.
 * @author cynthia
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(rest.RmiApiResource.class);
    }
    
}
