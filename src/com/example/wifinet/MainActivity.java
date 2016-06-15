package com.example.wifinet;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.wifinet.SecutiryForMessage;

public class MainActivity extends Activity {
	private Button buttonLogin;
	static final String IPADD = "172.30.61.131";
	static final int PORT = 3302;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		buttonLogin=(Button)findViewById(R.id.button1);
		buttonLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText editText1 = (EditText)findViewById(R.id.editText1);
				EditText editText2 = (EditText)findViewById(R.id.editText2);
				final String messageUser ="R "+editText1.getText()+" "+SecutiryForMessage.MD5ToEncrypt( editText2.getText().toString());
				
				new Thread(){
					@Override
					public void run(){
						
						String messageFromSever = FunctionToConnectWifi.connectToWifi(
								IPADD, PORT,messageUser);
						if(messageFromSever.equals("OKTOLOGIN")){
							
							
							Message msg = new Message();
							msg.what = 2;
							mHandler.sendMessage(msg);
							
							Intent intent=new Intent();
							intent.setClass(MainActivity.this, OperateActivity.class);
							MainActivity.this.startActivity(intent);
							
						}
						else{
							Message msg = new Message();
							msg.what = 1;
							msg.obj = messageFromSever;
							mHandler.sendMessage(msg);
						}
					}
				}.start();;
				
				
			}
		});
	}
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(getApplicationContext(), "Login Failed",
					     Toast.LENGTH_SHORT).show();
				break;
			case 2:
//				Toast.makeText(getApplicationContext(), "next activity",
//					     Toast.LENGTH_SHORT).show();
				break;
            }
          super.handleMessage(msg);
	 }
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
}
