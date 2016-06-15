package com.example.wifinet;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.R.integer;

public class FunctionToConnectWifi {
	public Socket socket;
	private  FunctionToConnectWifi(){
		socket = connectToWifi(MainActivity.IPADD, MainActivity.PORT);
	}
	
	
	private Socket connectToWifi(String IPADD,int PORT){
		try {System.out.println("socket to be created");
			Socket socket = new Socket(IPADD,PORT);
			return socket;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
	public void writeToWifi(Socket socket,String Message){

//		DataOutputStream writer = new DataOutputStream(  
//                socket.getOutputStream());
//		writer.writeUTF(Message);  
//        writer.flush();  
//        writer.close();           
		
		
		OutputStream out;
		try {
			out = socket.getOutputStream();
			out.write(Message.getBytes());
			System.out.println(Message);
			out.flush();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//        DataInputStream br = new DataInputStream(
//        		socket.getInputStream());
		
		
	}
	public String readFromWifi(Socket socket,byte[] buffRcv){
		System.out.println("data be sent");
//		InputStream in;
		InputStream in;
		
		try {
			
			in = socket.getInputStream();
			System.out.println("wwang");
			
			in.read(buffRcv);
			String line = new String(buffRcv,"utf-8"); 
//			in.close();
			System.out.println(line);
			return line.trim();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		
//        line=line.substring(0,"OKTOLOGIN".length());
//        System.out.println(line.toCharArray());
//        System.out.println("OKTOLOGIN");
        
//        br.close();  
//        socket.close();
        
	}
	private static FunctionToConnectWifi function = new FunctionToConnectWifi();
	public static FunctionToConnectWifi getFunction(){
		return function;
	}
}
