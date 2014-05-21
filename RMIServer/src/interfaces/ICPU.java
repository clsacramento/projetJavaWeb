/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @deprecated 
 * I didn't delete but no more used in the project
 * @author cynthia
 */
@XmlRootElement(name = "cpu")
public interface ICPU extends Serializable{
    @XmlElement
    public String getTotalUsed();
    @XmlElement
    public String getUserLoad();
    @XmlElement
    public String getSystemLoad();
    @XmlElement
    public String getIdle();
    @XmlElement
    public Date getDate();
}
