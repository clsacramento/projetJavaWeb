/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.Enumeration;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Damien
 */
public class AuthTest {

    public boolean auth(HttpSession session) {

        Enumeration<String> attributes = session.getAttributeNames();
        boolean authExist = false;
        while (attributes.hasMoreElements()) {
            String test = attributes.nextElement();
            if (test.equals("auth")) {
                authExist = true;
            }
        }
        return authExist;
    }
}
