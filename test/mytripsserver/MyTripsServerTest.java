/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytripsserver;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Darren
 */
public class MyTripsServerTest {
    
    //the server must be running
    
    @Test
    public void testServer() {
        try {
            assertTrue(socketTest("ds/bball"));
            assertFalse(socketTest("jdoe/1234"));
        } catch (Exception e) {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
    }
    
    private boolean socketTest(String credentials) throws Exception {
        boolean isAuthenticated = false;
        Socket socket = new Socket(InetAddress.getLocalHost(), 8000);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        oos.writeObject(credentials);
        isAuthenticated = (boolean)ois.readObject();
        oos.close();
        ois.close();
        socket.close();
        return isAuthenticated;
    }
    
}
