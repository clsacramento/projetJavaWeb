/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.io.Serializable;

/**
 *
 * @author cynthia
 */
public interface ICPU extends Serializable{
    public String getTotalUsed();
    public String getUserLoad();
    public String getSystemLoad();
    public String getIdle();
}
