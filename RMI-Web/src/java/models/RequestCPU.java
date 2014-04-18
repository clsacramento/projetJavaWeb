/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import interfaces.ICPU;
import java.util.Date;

/**
 *
 * @author cynthia
 */
public class RequestCPU extends Request
{
    private ICPU cpu;
    public RequestCPU(Server server, User user, String typeRequest, ICPU cpu) {
        super(server, user, typeRequest);
        this.cpu = cpu;
        this.date = cpu.getDate();
    }

    @Override
    public void saveRequestDetails() {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
