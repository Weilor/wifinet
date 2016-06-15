package com.example.wifinet;

import android.R.integer;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wifinet.FunctionToConnectWifi;
import com.example.wifinet.MainActivity;

public class OperateActivity extends ListActivity {
	private ListView m_listview;
	private String[] data;
	private ArrayAdapter<String> listItemAdapter;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(Menu.NONE,1,1,"Refresh");
		return super.onCreateOptionsMenu(menu);
	}
	
	

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 1:
			new Thread(){
				@Override
				public void run(){
					String MessageFromSever = FunctionToConnectWifi.connectToWifi(MainActivity.IPADD, MainActivity.PORT, "Q");
					data = MessageFromSever.split(" ");
					Message msg = new Message();
					msg.what = 0;
					msg.obj = MessageFromSever;
					mHandler.sendMessage(msg);
				}
			}.run();
        	break;
        }
		return super.onMenuItemSelected(featureId, item);
	}
	@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			Message msg = new Message();
			msg.what = 0;
			mHandler.sendMessage(msg);
		}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_operate);
		m_listview = new ListView(this);
		new Thread(){
			@Override
			public void run(){
				String MessageFromSever = FunctionToConnectWifi.connectToWifi(MainActivity.IPADD, MainActivity.PORT, "Q");
				data = MessageFromSever.split(" ");
				Message msg = new Message();
				msg.what = 0;
				msg.obj=MessageFromSever;
				mHandler.sendMessage(msg);
			}
		}.run();
		
		
		
		
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent();
		intent.setClass(OperateActivity.this, DialogActivity.class);
		Bundle bundleToDlg = new Bundle();
		bundleToDlg.putString("v_id", Integer.toString(position));
		intent.putExtras(bundleToDlg);
		this.startActivity(intent);
		new Thread(){
			@Override
			public void run(){
				String MessageFromSever = FunctionToConnectWifi.connectToWifi(MainActivity.IPADD, MainActivity.PORT, "Q");
				data = MessageFromSever.split(" ");
				
			}
		}.run();
	}
Handler mHandler = new Handler(){
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case 0:
			listItemAdapter = new ArrayAdapter<String>(OperateActivity.this, 
					android.R.layout.simple_expandable_list_item_1, data);
			setListAdapter(listItemAdapter);
//			Toast.makeText(getApplicationContext(), (String)msg.obj,
//				     Toast.LENGTH_SHORT).show();
			break;
//		case 1:
//			listItemAdapter.notifyDataSetChanged();
//			Toast.makeText(getApplicationContext(), (String)msg.obj,
//				     Toast.LENGTH_SHORT).show();
//			break;
        }
      super.handleMessage(msg);
 }
};

}
