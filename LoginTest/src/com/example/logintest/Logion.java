package com.example.logintest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import android.os.Handler;
import android.os.Message;

public class Logion {


	public static boolean check(String name, String password) {
		// TODO Auto-generated method stub
		String urlStr="http://172.21.20.178/chek_login.php";
		Map<String,String> user=new HashMap<String,String>();
		user.put("name",name);
		user.put("pssword",password);
		try{
			return SendGETReauest(urlStr,user, "UTF-8"); 
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	
	
	private static boolean SendGETReauest(String urlStr,
			Map<String, String> user,String ecoding) throws Exception{
		// TODO Auto-generated method stub
		StringBuilder url=new StringBuilder(urlStr);
		url.append("?");
		for(Map.Entry<String, String> map:user.entrySet()){
			url.append(map.getKey()).append("=");
			url.append(URLEncoder.encode(map.getValue(),ecoding));
			url.append("&"); 
		}
		url.deleteCharAt(url.length()-1);
        System.out.println(url);
        HttpsURLConnection conn = (HttpsURLConnection)new URL(url.toString()).openConnection();
        conn.setConnectTimeout(100000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200){       return true;     } 
		
		return false;
	}

	public static int checkLogin() throws IOException {
		String result;
		int code=0;
        URL url=new URL("http://172.21.20.178/chek_login.php?name=111&password=222");
        HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
        InputStreamReader isr=new InputStreamReader(urlConnection.getInputStream());
        BufferedReader br=new BufferedReader(isr);
        result=br.readLine();
        try {
            JSONObject object=new JSONObject(result);
            code=object.getInt("code");
            JSONArray ja=  object.getJSONArray("data");
            String data=ja.getString(0);
            JSONObject secondobject=new JSONObject(data);
       } catch (JSONException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
		return code;
		// TODO Auto-generated method stub
	
          
		
	}
}


