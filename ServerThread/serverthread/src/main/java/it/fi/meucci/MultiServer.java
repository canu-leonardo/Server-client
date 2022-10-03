package it.fi.meucci;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {
    public void avvia(){
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            for(;;){
                System.out.println("Server in attesa...");
                Socket socket = serverSocket.accept();
                System.out.println("Socket: " + socket);
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("errore");
        }
    }
}
