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
 *
 * @author cynthia
 */
@XmlRootElement(name = "exception")
public class ExceptionXml implements Serializable{
    private String type;
    private String message;
    
    public ExceptionXml(){
        this.type = "No Exception";
        this.message = "No Exception happened until now.";
    }
    
    
    public ExceptionXml(Exception ex){
        this.type = ex.getClass().getName();
        this.message = ex.getMessage();
    }
    
    @XmlElement
    public void setExceptionType(String exceptionType){
        this.type = exceptionType;
    }
    public String getExceptionType(){
        return this.type;
    }
    
    
    
    @XmlElement
    public void setExceptionMessage(String exceptionMessage){
        this.message = exceptionMessage;
    }
    public String getExceptionMessage(){
        return this.message;
    }
}
