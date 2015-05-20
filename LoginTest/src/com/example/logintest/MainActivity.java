package com.example.logintest;

import java.io.UnsupportedEncodingException;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	 private EditText textname = null; 
	  private EditText textpassword = null; 
	  private Button button = null; 
	  private TextView te=null;
	  private static Handler handler=new Handler();
	  @Override
	  protected void onCreate(Bundle savedInstanceState) { 
	    super.onCreate(savedInstanceState); 
	    setContentView(R.layout.fragment_main);
	    textname = (EditText)findViewById(R.id.name); 
	    textpassword = (EditText)findViewById(R.id.password); 
	    button = (Button)findViewById(R.id.button); 
	       
	    button.setOnClickListener(new mybuttonlistener()); 
	       
	  } 
	  class mybuttonlistener implements OnClickListener{ 
	    int result; 
	    String name; 
	    String password;
	    public void onClick(View v) {
	    	
	    	 new Thread(){
	    		

				@Override
	             public void run() {
	                               try {         
	                                     name = textname.getText().toString(); 
	                                     name = new String(name.getBytes("ISO8859-1"), "UTF-8"); 
	                                     password = textpassword.getText().toString(); 
	                                     password = new String(password.getBytes("ISO8859-1"), "UTF-8"); 
	                                     } catch (UnsupportedEncodingException e1) { 
	                                    	 // TODO Auto-generated catch block 
	                                     e1.printStackTrace(); 
	                                     }try { 
	                                    	 result = NewsService.login(name,password);
	                                    	 } catch (Exception e) { 
	                                    		 // TODO Auto-generated catch block 
	                                    		 e.printStackTrace(); 
	                                    		 }
	                                     handler.postDelayed(new Runnable() {
	                                    	                              
	                                    	                              @Override
	                                    	                              public void run() {
	                                    	                            	  Intent intent = new Intent();
	                                    	                     	    	 if(result==1){ 
	                                    	                            		 Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
	                                    	                            		 intent.setClass(MainActivity.this, ManageActivity.class);
	                                    	                            		 startActivity(intent);
	                                    	                            		 }else{ 
	                                    	                            			 Toast.makeText(MainActivity.this, "erro", Toast.LENGTH_SHORT).show(); 
	                                    	                            			 } 
	                                    	                              }
	                                    	                          }, 3000);       
	                                     
	             }
	    	 }.start();

	    }
	  }
}

