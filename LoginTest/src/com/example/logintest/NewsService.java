package com.example.logintest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewsService {

	public static int save(String name, String password){ 
	    String path = "http://172.21.20.178/chek_login.php?name=111&password=222";
	    int b;
	    try { 
	      return SendGETRequest(path);  
	    } catch (Exception e) { 
	      // TODO Auto-generated catch block 
	      e.printStackTrace(); 
	    } 
	    return 0; 
	  } 
	public static int login(String name, String password){ 
	   if(name.equals("111")&&password.equals("222")){
		   return 1;
	   }
	   else
		   return 0;
	  } 

	 private static int SendGETRequest(String path) throws Exception{ 
		    // http://127.0.0.1:8080/Register/ManageServlet?name=1233&password=abc 
		    String result;
		    StringBuilder url = new StringBuilder(path); 
		    InputStream is= (new URL(url.toString())).openStream();
	        BufferedReader in2=new BufferedReader(new InputStreamReader(is));
	        
	        //HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
			//InputStreamReader isr=new InputStreamReader(urlConnection.getInputStream());
			//BufferedReader br=new BufferedReader(isr);
		    return Integer.parseInt(in2.readLine()); 
		  } 
		}
