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
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author cynthia
 */
public class RequestProcess extends Request{
    private final List<IProcess> processList;
    private HashMap <Integer,IProcess> processesRequestsIds;
    
    public RequestProcess(Server server, User user, String typeRequest, List<IProcess> processList) {
        super(server, user, typeRequest);
        this.processList = processList;
        if(processList.size()>0){
            this.date = this.processList.get(0).getDate();
        }
        this.processesRequestsIds = new HashMap<>();
    }

    @Override
    public void saveRequestDetails() throws SQLException, DataBaseDriverMissingException, DataBaseConnectionInformationFileNotFoundException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        this.saveRequest();
        if(processList.size()>0){
            for(IProcess process : processList){
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
    
}
