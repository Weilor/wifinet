package com.example.wifinet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Message;

public class FunctionToConnectWifi {
	public final static String connectToWifi(String IPADD,int PORT,String Message){
		try {
			Socket socket = new Socket(IPADD,PORT);
			
			DataOutputStream writer = new DataOutputStream(  
                    socket.getOutputStream());
			writer.writeUTF(Message);  
            writer.flush();  
//            writer.close();           
			
            DataInputStream br = new DataInputStream(
            		socket.getInputStream());
            String line = br.readUTF(); 
            
//            line=line.substring(0,"OKTOLOGIN".length());
//            System.out.println(line.toCharArray());
//            System.out.println("OKTOLOGIN");
            
            br.close();  
            socket.close();
            return line;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "UnkmownHostExeption";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
}
