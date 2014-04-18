/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author cynthia
 */
public class User {
    private int id;
    private String login;
    
    public User(String login){
        this.login = login;
    }
    
    public int getId(){
        return this.id;
    }
}
