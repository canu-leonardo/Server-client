package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
    ServerSocket server      = null;
    Socket client            = null;
    String stringaRicevuta   = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoIlClient;

    public ServerThread (Socket socket){
        this.client = socket;
    }

    public void run(){
        try {
            comunica();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void comunica() throws Exception{
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoIlClient = new DataOutputStream(client.getOutputStream());

        for (;;){
            stringaRicevuta = inDalClient.readLine();
            if (stringaRicevuta == null || stringaRicevuta == "FINE"){
                outVersoIlClient.writeBytes(stringaRicevuta + "(=> server in chiusura...)" + "\n");
                break;
            }
            else{
                outVersoIlClient.writeBytes(stringaRicevuta.toUpperCase()+ "\n");
                System.out.println("fine elaborazione");
            }
        }
        outVersoIlClient.close();
        inDalClient.close();
        client.close();
    }
}
