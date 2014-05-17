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
import java.util.Date;

/**
 *
 * @author cynthia
 */
public abstract class Request {
    protected int id;
    protected Server server;
    protected User user;
    protected int typeRequestId;
    protected Date date;
    
    public static int getTypeRequestId(String typeRequest){
        switch(typeRequest){
            case "getListOfProcesses":
                return 1;
            case "getCPU":
                return 2;
            case "getPhysicalMemory":
                return 3;
            default:
                return 0;
        }
    }
    
    public Request(Server server, User user, String typeRequest){
        this.server = server;
        this.date = date;
        this.user = user;
        this.typeRequestId = Request.getTypeRequestId(typeRequest);
    }
    
    public int getId(){
        return this.id;
    }
    
    public void saveRequest() throws SQLException, DataBaseDriverMissingException, DataBaseConnectionInformationFileNotFoundException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        java.sql.Date d = new java.sql.Date(this.date.getTime());
        java.sql.Time t = new java.sql.Time(this.date.getTime());
        
        this.id = RequestDAO.insertRequest(this.server.getId(), this.user.getId(), this.typeRequestId, d+" "+t);
        
        if(this.id == 0){
            
        }
    }
    
    public abstract void saveRequestDetails() throws SQLException, DataBaseDriverMissingException, DataBaseConnectionInformationFileNotFoundException, DataBaseInformationFileParsingException, DataBaseConnectionException;
}
