/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package osutils;

import interfaces.ICPU;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Damien
 */
public class CPU implements ICPU {

    private String total;
    private String userLoad;
    private String systemLoad;
    private String idle;
    
    
    CPU (String total,String userLoad,String systemLoad,String idle) {
        this.total = total;
        this.userLoad = userLoad;
        this.systemLoad = systemLoad;
        this.idle = idle;
    }
    
    @Override
    public String getTotalUsed() {
        return total;
    }

    @Override
    public String getUserLoad() {
        return userLoad;
    }

    @Override
    public String getSystemLoad() {
        return systemLoad;
    }

    @Override
    public String getIdle() {
        return idle;
    }
    
    
    @Override
    public String toString(){
        return (this.total != null ? this.total : "" )+
                (this.systemLoad != null ? this.systemLoad+"," : "" )+
                (this.userLoad != null ? this.userLoad+"," : "" )+
                (this.idle != null ? this.idle : "" );
    }
}
