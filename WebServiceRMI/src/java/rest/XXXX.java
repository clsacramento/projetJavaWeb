/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cynthia
 */
@XmlRootElement(name = "xxxx")
public class XXXX {
    public XXXX(){
        
    }
    @XmlElement
    public String getXXXX(){
        return "xxxx";
    }
        
}
