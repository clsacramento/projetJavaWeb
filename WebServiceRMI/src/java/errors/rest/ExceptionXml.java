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
    private Exception ex;
    
    public ExceptionXml(){
        this.ex = null;
    }
    
    public ExceptionXml(Exception ex){
        this.ex = ex;
    }
    
    @XmlElement
    public String getExceptionType(){
        return this.ex.getClass().getName();
    }
    
    @XmlElement
    public String getExceptionMessage(){
        return this.ex.getMessage();
    }
}
