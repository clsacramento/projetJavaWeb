/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.authentication;

/**
 *
 * @author Damien
 */
public class UserNotValidException extends Exception{
    public UserNotValidException(){
        super ("User or password not valid");
    }
}
