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
import osutils.Memory;

/**
 * Specific Request for Memory
 * 
 * @author cynthia
 */
public class RequestMemory extends Request {
    private Memory mem;
    private int idRequestMemory;
    
    /**
     * Constructs object from DAO HashMap
     * @param requestDAO 
     */
    public RequestMemory(HashMap requestDAO){
        super(requestDAO);
        mem = null;
    }

    /**
     * Constructs request from parameters
     * @param server
     * @param user
     * @param typeRequest
     * @param mem 
     */
    public RequestMemory(Server server, User user, String typeRequest, Memory mem) {
        super(server, user, typeRequest);
        this.mem = mem;
        this.date = mem.getDate();
    }
    
    /**
     * Retrieves request Memory usage collected from database
     * @return Memory instance
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public Memory getMemory() throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        if(this.mem == null){
            HashMap memFields = RequestDAO.selectMemory(this.id);
            if(memFields != null){
                this.mem = new Memory(
                        (String)memFields.get("total")
                        ,(String)memFields.get("used")
                        ,(String)memFields.get("free"));
            }
        }
        return this.mem;
    }

    /**
     * Register memory request specific information
     * @throws SQLException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    @Override
    public void saveRequestDetails() throws SQLException, DataBaseDriverMissingException, DataBaseConnectionInformationFileNotFoundException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        this.saveRequest();
        this.idRequestMemory = RequestDAO.insertRequestMemory(
                this.id, mem.getTotal(), 
                mem.getFree(), 
                mem.getUsed());
    }

    /**
     * Creates the field/value mapping of the request specific information
     * @return HashMap
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    @Override
    public HashMap<String, String> getDetails()throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        HashMap<String,String> details = new HashMap<>();
        
        this.getMemory();
        
        details.put("Total memory", this.mem.getTotal());
        details.put("Free", this.mem.getFree());
        details.put("Used", this.mem.getUsed());
        
        return details;
    }
    
}
