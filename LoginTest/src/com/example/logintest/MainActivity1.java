package com.example.logintest;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.example.logintest.MainActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity1 extends Activity {
	private static HttpClient httpclient; // 生命一个静态的全局HttpClient对象
	private EditText yhName = null;
	private EditText yhPassword = null;
	private TextView te=null;
	private String username;
	private String pwd;
	String strResult;
	String code;
	HttpResponse httpResponse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		yhName = (EditText) findViewById(R.id.yhName);
		yhPassword = (EditText) findViewById(R.id.yhPassword);

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				username = yhName.getText().toString();
				pwd = yhPassword.getText().toString();
				Thread threadB = new Thread(new Runnable() {
					 
					public void run() {
						try {
							String target = "http://172.21.20.178:8080/bysja/Process"; // 要提交的目标地址
							final HttpPost httpRequest = new HttpPost(target); // 创建HttpPost对象
							httpclient=new DefaultHttpClient();
							// 将要传递的参数保存到List集合中
							final List<NameValuePair> params = new ArrayList<NameValuePair>();
							params.add(new BasicNameValuePair("userName", username)); // 用户名
							params.add(new BasicNameValuePair("password", pwd)); // 密码
							httpRequest.setEntity(new UrlEncodedFormEntity(params,"utf-8")); // 设置编码方式
							httpResponse = httpclient.execute(httpRequest);
							strResult = EntityUtils.toString(httpResponse.getEntity());
							JSONObject object=new JSONObject(strResult);
							code=object.getString("username");
							System.out
									.println("-------------------------------------------");

						} catch (Exception e) {
							// TODO: handle exception
						}

					}

				});

				threadB.start();
				Toast.makeText(MainActivity1.this,code, Toast.LENGTH_SHORT).show(); 
			}
		});
	}

}
