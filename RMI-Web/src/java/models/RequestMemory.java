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
import interfaces.IPhysicalMemory;
import java.sql.SQLException;

/**
 *
 * @author cynthia
 */
public class RequestMemory extends Request {
    private final IPhysicalMemory mem;
    private int idRequestMemory;

    public RequestMemory(Server server, User user, String typeRequest, IPhysicalMemory mem) {
        super(server, user, typeRequest);
        this.mem = mem;
        this.date = mem.getDate();
    }

    @Override
    public void saveRequestDetails() throws SQLException, DataBaseDriverMissingException, DataBaseConnectionInformationFileNotFoundException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        this.saveRequest();
        this.idRequestMemory = RequestDAO.insertRequestMemory(
                this.id, mem.getTotal(), 
                mem.getFree(), 
                mem.getUsed());
    }
    
}
