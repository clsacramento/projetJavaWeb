/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import DAL.UserDAO;
import errors.database.DataBaseConnectionException;
import errors.database.DataBaseConnectionInformationFileNotFoundException;
import errors.database.DataBaseDriverMissingException;
import errors.database.DataBaseInformationFileParsingException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author cynthia
 */
public class User {
    private int id;
    private final String login;
    private String password;
    
    public User(int id, String login){
        this.id = id;
        this.login = login;
    }
    
    public User(String login, String password){
        this.login = login;
        this.password = password;
    }
    
    public User(HashMap userDAO){
        this.id = Integer.parseInt((String)userDAO.get("id_user"));
        this.login = (String)userDAO.get("login");
    }

    public boolean validateUser () throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        HashMap user = UserDAO.selectUser(login, password);
        if(user!=null)
        {
            this.id = Integer.parseInt((String) user.get("id_user"));
            return true;
        }
        return false;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getLogin(){
        return this.login;
    }
}
