/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytripsserver;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Darren
 */
public class SocketThread implements Runnable{
    
    private Socket socket;
    
    public SocketThread(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() { //thread starts here
        try {
            System.out.println("Accepted connection in new thread");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String credentials = (String)ois.readObject();
            boolean isAuthenticated = authenticate(credentials);
            oos.writeObject(isAuthenticated); //or: oos.writeObject( new Boolean(isAuthenticated) )
            oos.close();
            ois.close();
            socket.close();
            //Thread.sleep(10000); //use to confirm that multiple threads are being used
            System.out.println("Exiting thread");
        }catch(Exception e) {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
    }
    
    private boolean authenticate(String credentials) {
        String[] match = credentials.split("/");
        if(match.length==2 && match[0].equals("ds") && match[1].equals("bball")) {
            return true;
        }
        else {
            return false;
        }
    }
    
}
