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
import interfaces.IProcess;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author cynthia
 */
public class RequestProcess extends Request{
    private List<osutils.Process> processList;
    private HashMap <Integer,osutils.Process> processesRequestsIds;
    
    public RequestProcess(HashMap requestDAO){
        super(requestDAO);
        this.processList = null;
    }
    
    public RequestProcess(Server server, User user, String typeRequest, List<osutils.Process> processList) {
        super(server, user, typeRequest);
        this.processList = processList;
        if(processList.size()>0){
            this.date = this.processList.get(0).getDate();
        }
        this.processesRequestsIds = new HashMap<>();
    }
    
    public List<osutils.Process> getProcessList() throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        if(this.processList == null){
            this.processList = new ArrayList<>();
            ArrayList<HashMap> proReqs = RequestDAO.selectProcessList(this.id);
            for(HashMap proReq : proReqs){
                this.processList.add(new osutils.Process(
                        (String) proReq.get("PID"), 
                        (String) proReq.get("name"), 
                        (String) proReq.get("using_cpu"), 
                        (String) proReq.get("cpu_time"), 
                        (String) proReq.get("state"), 
                        (String) proReq.get("using_memory"), 
                        (String) proReq.get("user")));
                        
            }
        }
        return this.processList;
    }

    @Override
    public void saveRequestDetails() throws SQLException, DataBaseDriverMissingException, DataBaseConnectionInformationFileNotFoundException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        this.saveRequest();
        if(processList.size()>0){
            for(osutils.Process process : processList){
                int processId = RequestDAO.insertRequestProcess(
                        this.id, 
                        process.getPID(), 
                        process.getName(), 
                        process.getUsingCPU(), 
                        process.getCPUTime(), 
                        process.getState(), 
                        process.getUsingMemory(), 
                        process.getUser());
                this.processesRequestsIds.put(processId, process);
            }
        }
    }

    @Override
        public HashMap<String, String> getDetails()throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        HashMap<String,String> details = new HashMap<>();
        
        this.getProcessList();
        
        for(osutils.Process p : this.processList){
            details.put(p.getPID(), 
                            p.getName()+","+
                            p.getUsingCPU()+","+
                            p.getCPUTime()+","+
                            p.getState()+","+
                            p.getUsingMemory()+","+
                            p.getUser());
        }
        
        return details;
    }
    
}
