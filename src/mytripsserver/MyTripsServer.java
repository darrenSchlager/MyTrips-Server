/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytripsserver;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Darren
 */
public class MyTripsServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Launching server");
        new MyTripsServer().run();
    }
    
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(8000, 100); //listening on port 8000, queue length of 100
            System.out.println("Starting listener on port 8000");
            while(true) {
                Socket socket = ss.accept(); // blocked, waiting for a socket connection
                SocketThread socketThread = new SocketThread(socket);
                Thread thread = new Thread(socketThread);
                thread.start();
            }
        } catch(Exception e) {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
    }
}
