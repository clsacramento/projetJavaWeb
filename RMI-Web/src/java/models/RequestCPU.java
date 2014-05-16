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

/**
 *
 * @author cynthia
 */
public class RequestCPU extends Request
{
    private ICPU cpu;
    private int idRequestCPU;
    
    public RequestCPU(Server server, User user, String typeRequest, ICPU cpu) {
        super(server, user, typeRequest);
        this.cpu = cpu;
        this.date = cpu.getDate();
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
    
}
