/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import DAL.ServerDAO;
import DAL.UserDAO;
import errors.database.DataBaseConnectionException;
import errors.database.DataBaseConnectionInformationFileNotFoundException;
import errors.database.DataBaseDriverMissingException;
import errors.database.DataBaseInformationFileParsingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import models.Server;
import models.User;

/**
 *
 * @author Damien
 */
public class UsersController {
    public static ArrayList<User> getUsers() throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
       ArrayList<User> users = new ArrayList();
       ArrayList<HashMap> usersDAO = DAL.UserDAO.selectUsers();
       for (HashMap userDAO: usersDAO) {
           User user = new User(userDAO);
           users.add(user);
       }
        return users;
        
    }

    public static void deleteUser(String id) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        UserDAO.delUser(id);
    }

    static User addUser(String login, String password) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        return new User(UserDAO.insertUser(login,password));
    }
}
