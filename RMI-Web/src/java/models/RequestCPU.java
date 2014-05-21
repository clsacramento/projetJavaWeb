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
import java.sql.SQLException;
import java.util.HashMap;
import osutils.CPU;

/**
 * Request specific for CPU usage
 * 
 * @author cynthia
 */
public class RequestCPU extends Request
{
    private CPU cpu;
    private int idRequestCPU;
    
    /**
     * Constructs object from DAO HashMap
     * @param requestDAO 
     */
    public RequestCPU(HashMap requestDAO){
        super(requestDAO);
        cpu = null;
    }
    
    /**
     * Constructs RequestCPU from parameters
     * @param server
     * @param user
     * @param typeRequest
     * @param cpu 
     */
    public RequestCPU(Server server, User user, String typeRequest, CPU cpu) {
        super(server, user, typeRequest);
        this.cpu = cpu;
        this.date = cpu.getDate();
    }
    
    /**
     * Gets CPU usage from the request in the history
     * @return instance of CPU
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public CPU getCPU() throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
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

    /**
     * Registers CPU usage of the request
     * @throws SQLException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    @Override
    public void saveRequestDetails() throws SQLException, DataBaseDriverMissingException, DataBaseConnectionInformationFileNotFoundException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        this.saveRequest();
        this.idRequestCPU = RequestDAO.insertRequestCPU(
                this.id, this.cpu.getTotalUsed(), 
                cpu.getUserLoad(), 
                cpu.getSystemLoad(), 
                cpu.getIdle());
    }

    /**
     * Maps fields/values of specific request information
     * @return HashMap
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
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
