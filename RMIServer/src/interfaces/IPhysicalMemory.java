/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.io.Serializable;
import java.util.Date;

/**
 * @deprecated 
 * I didn't delete but no more used in the project
 * @author cynthia
 */
public interface IPhysicalMemory extends Serializable{
    public String getUsed();
    public String getFree();
    public String getTotal();
    public Date getDate();

}
