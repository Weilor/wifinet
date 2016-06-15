package com.example.wifinet;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wifinet.FunctionToConnectWifi;

public class DialogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);
		Button btnOK ;
		Button btnCancel;
		final EditText textValue;
		
		btnOK = (Button)findViewById(R.id.btnyes);
		btnCancel = (Button)findViewById(R.id.btnno);
		textValue = (EditText)findViewById(R.id.editText3);
//		final String valueOfUser =textValue.getText().toString();
		btnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(){
					@Override
					public void run(){
						FunctionToConnectWifi function = FunctionToConnectWifi.getFunction();
						System.out.println(DialogActivity.this.getIntent().getExtras().getString("v_id"));
//						function.connectToWifi(MainActivity.IPADD, MainActivity.PORT);
						function.writeToWifi(function.socket,
								"C "+DialogActivity.this.getIntent().getExtras().getString("v_id")+" "+textValue.getText().toString());
						String messageFromSever = function.readFromWifi(function.socket,new byte[128]);
						
						if(messageFromSever.equals("OKTOCONTROL")){
							finish();
						}
						else{
							Message msg = new Message();
							msg.what = 1;
							msg.obj = messageFromSever;
							mHandler.sendMessage(msg);
						}
					}
				}.start();
			}
		});
		btnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(getApplicationContext(), "Control Failed:"+msg.obj,
					     Toast.LENGTH_SHORT).show();
				finish();
				break;
            }
          super.handleMessage(msg);
	 }
	};
}
