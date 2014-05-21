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

/** 
 * Request (Generic)
 * 
 * A class defining a server request
 * @author cynthia
 */
public abstract class Request {
    /**
     * request id
     */
    protected int id;
    /**
     * Server to which the request was made
     */
    protected Server server;
    /**
     * User who made the request
     */
    protected User user;
    /**
     * Type of request (CPU,Memory,Process)
     */
    protected String typeRequest;
    /**
     * Corresponding type request id
     */
    protected int typeRequestId;
    /**
     * timestamp of the request execution (server side)
     */
    protected Date date;
    
    /**
     * HashMap mapping these fields to their values.
     */
    protected HashMap<String,String> fields;
    
    /**
     * Given a request type returns its id.
     * These ids are the same of the ones in the database but it is done locally
     * to avoid one database connection jus for it.
     * It is important to notice though that if these values are static and if
     * they ever change in the database it is necessary to modify this function.
     * @param typeRequest
     * @return 
     */
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
    
    /**
     * Creates a request from a database fields/values mapping
     * @param requestDAO 
     */
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
    
    /**
     * Creates the request from its given parameters
     * @param server
     * @param user
     * @param typeRequest 
     */
    public Request(Server server, User user, String typeRequest){
        this.server = server;
        this.user = user;
        this.typeRequest = typeRequest;
        this.typeRequestId = Request.getTypeRequestId(typeRequest);
        this.fields = null;
    }
    
    /**
     * Mapping of fields and values
     * @return HashMap
     */
    public HashMap<String,String> getFields(){
        return this.fields;
    }
    
    /**
     * Mapping of detailed fields/values matches.
     * @return HashMap
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
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
    
    
    /**
     * Registers this request to the database
     * @throws SQLException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public void saveRequest() throws SQLException, DataBaseDriverMissingException, DataBaseConnectionInformationFileNotFoundException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        java.sql.Date d = new java.sql.Date(this.date.getTime());
        java.sql.Time t = new java.sql.Time(this.date.getTime());
        
        this.id = RequestDAO.insertRequest(this.server.getId(), this.user.getId(), this.typeRequestId, d+" "+t);
        
        if(this.id == 0){
            
        }
    }
    /**
     * Creates an specific request from DAO (HashMap) depending on its type
     * @param requestDAO
     * @return an specific Request (CPU,Processes or Memory)
     */
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
    /**
     * Retrieve list of requests
     * @return Mapping of request ids to corresponding Request instance
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public static HashMap<Integer, Request> getRequests() throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        HashMap<Integer,Request> reqs = new HashMap<>();
        
        ArrayList<HashMap> reqsDAO = RequestDAO.selectRequests();
        
        for(HashMap reqDAO : reqsDAO){
            Request req = Request.createRequest(reqDAO);
            reqs.put(req.getId(),req);
        }
        
        return reqs;
    }
    /**
     * Registers request specific info to the database
     * @throws SQLException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public abstract void saveRequestDetails() throws SQLException, DataBaseDriverMissingException, DataBaseConnectionInformationFileNotFoundException, DataBaseInformationFileParsingException, DataBaseConnectionException;
}
