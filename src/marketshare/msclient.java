/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketshare;

/**
 *
 * @author yajnavalkya
 */
import java.io.*;
import java.net.*;

public class msclient {    
    public void connect(String content) throws Exception {
        try (Socket clientSocket = new Socket("cubie", 6789)) {
            System.out.println("Connected");
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeChars(content+"\n");            
        }catch(Exception e){
            System.out.println(e);
        }
    }
}