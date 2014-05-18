/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import DAL.RequestDAO;
import errors.database.DataBaseConnectionException;
import errors.database.DataBaseConnectionInformationFileNotFoundException;
import errors.database.DataBaseDriverMissingException;
import errors.database.DataBaseInformationFileParsingException;
import interfaces.ICPU;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import osutils.CPU;

/**
 *
 * @author cynthia
 */
public class RequestCPU extends Request
{
    private ICPU cpu;
    private int idRequestCPU;
    
    public RequestCPU(HashMap requestDAO){
        super(requestDAO);
        cpu = null;
    }
    
    public RequestCPU(Server server, User user, String typeRequest, ICPU cpu) {
        super(server, user, typeRequest);
        this.cpu = cpu;
        this.date = cpu.getDate();
    }
    
    public ICPU getCPU() throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        if(this.cpu == null){
            HashMap cpuFields = RequestDAO.selectCPU(this.id);
            if(cpuFields != null){
                this.cpu = new CPU(
                        (String)cpuFields.get("total")
                        ,(String)cpuFields.get("user_load")
                        ,(String)cpuFields.get("system_load")
                        ,(String)cpuFields.get("idle"));
            }
        }
        return this.cpu;
    }

    @Override
    public void saveRequestDetails() throws SQLException, DataBaseDriverMissingException, DataBaseConnectionInformationFileNotFoundException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        this.saveRequest();
        this.idRequestCPU = RequestDAO.insertRequestCPU(
                this.id, this.cpu.getTotalUsed(), 
                cpu.getUserLoad(), 
                cpu.getSystemLoad(), 
                cpu.getIdle());
    }

    @Override
    public HashMap<String, String> getDetails() throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        HashMap<String,String> details = new HashMap<>();
        
        this.getCPU();
       
        details.put("Total used", this.cpu.getTotalUsed());
        details.put("User load", this.cpu.getUserLoad());
        details.put("System load", this.cpu.getSystemLoad());
        details.put("idle", this.cpu.getIdle());
        
        return details;
    }
    
}
