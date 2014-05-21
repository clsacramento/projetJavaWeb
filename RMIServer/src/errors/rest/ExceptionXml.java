/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.rest;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An XML representation of an exception.
 * This objects wraps an exception to be serialized and exposed through the
 * web service.
 * @author cynthia
 */
@XmlRootElement(name = "exception")
public class ExceptionXml implements Serializable{
    /**
     * Exception being wrapped.
     */
    private Exception ex;
    
    public ExceptionXml(){
        this.ex = null;
    }
    
    public ExceptionXml(Exception ex){
        this.ex = ex;
    }
    
    /**
     * 
     * @return String type of encapsulated exception
     */
    @XmlElement
    public String getExceptionType(){
        return this.ex.getClass().getName();
    }
    
    /**
     * 
     * @return String its error message
     */
    @XmlElement
    public String getExceptionMessage(){
        return this.ex.getMessage();
    }
}
