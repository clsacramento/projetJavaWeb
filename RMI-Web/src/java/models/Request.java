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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author cynthia
 */
public abstract class Request {
    protected int id;
    protected Server server;
    protected User user;
    protected String typeRequest;
    protected int typeRequestId;
    protected Date date;
    
    protected HashMap<String,String> fields;
    
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
    
    public Request(HashMap requestDAO){
        fields = new HashMap<>();
        fields.put("Request Id", (String) requestDAO.get("id_request"));
        this.id = Integer.parseInt((String) requestDAO.get("id_request"));
        this.server = new  Server(
                Integer.parseInt(
                        (String) requestDAO.get("id_server")), 
                        (String) requestDAO.get("host"));
        fields.put("Server", this.server.getHost());
        this.user = new  User(
                Integer.parseInt(
                        (String) requestDAO.get("id_user")), 
                        (String) requestDAO.get("login"));
        fields.put("User", this.user.getLogin());
        Timestamp t = (Timestamp) requestDAO.get("timestamp");
        this.date = t;
        fields.put("Request Date", this.date.toString());
        this.typeRequest = (String) requestDAO.get("type_request");
        this.fields.put("Request Type", typeRequest);
        this.typeRequestId = Integer.parseInt((String) requestDAO.get("id_type_request"));
    }
    
    public Request(Server server, User user, String typeRequest){
        this.server = server;
        this.user = user;
        this.typeRequest = typeRequest;
        this.typeRequestId = Request.getTypeRequestId(typeRequest);
        this.fields = null;
    }
    
    public HashMap<String,String> getFields(){
        return this.fields;
    }
    
    public abstract HashMap<String,String> getDetails()throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException;
    
    public int getId(){
        return this.id;
    }

    public Server getServer() {
        return server;
    }

    public User getUser() {
        return user;
    }

    public String getTypeRequest() {
        return typeRequest;
    }

    public int getTypeRequestId() {
        return typeRequestId;
    }

    public Date getDate() {
        return date;
    }
    
    
    
    public void saveRequest() throws SQLException, DataBaseDriverMissingException, DataBaseConnectionInformationFileNotFoundException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        java.sql.Date d = new java.sql.Date(this.date.getTime());
        java.sql.Time t = new java.sql.Time(this.date.getTime());
        
        this.id = RequestDAO.insertRequest(this.server.getId(), this.user.getId(), this.typeRequestId, d+" "+t);
        
        if(this.id == 0){
            
        }
    }
    
    private static Request createRequest(HashMap requestDAO){
        String typeRequest = (String) requestDAO.get("type_request");
        switch(typeRequest){
            case "getListOfProcesses":
                return new RequestProcess(requestDAO);
            case "getCPU":
                return new RequestCPU(requestDAO);
            case "getPhysicalMemory":
                return new RequestMemory(requestDAO);
            default:
                return null;
        }
    }
    
    public static HashMap<Integer, Request> getRequests() throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        HashMap<Integer,Request> reqs = new HashMap<>();
        
        ArrayList<HashMap> reqsDAO = RequestDAO.selectRequests();
        
        for(HashMap reqDAO : reqsDAO){
            Request req = Request.createRequest(reqDAO);
            reqs.put(req.getId(),req);
        }
        
        return reqs;
    }
    
    public abstract void saveRequestDetails() throws SQLException, DataBaseDriverMissingException, DataBaseConnectionInformationFileNotFoundException, DataBaseInformationFileParsingException, DataBaseConnectionException;
}
