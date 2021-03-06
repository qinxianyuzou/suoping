package com.example.keyguard;


import com.example.activity.Activity_earnings;
import com.example.activity.Activity_invitation;
import com.example.activity.Activity_more;
import com.example.activity.Activity_shop;
import com.example.parameter.config;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends TabActivity implements OnCheckedChangeListener{

		private TabHost tabHost;
		private RadioGroup radioderGroup;
		
		private static String TAG = "QINZDLOCK";

		private RelativeLayout sliderLayout = null;

		private AnimationDrawable animArrowDrawable = null;

	    private Context mContext = null ;
		
	    public static int MSG_LOCK_SUCESS = 1;

		@SuppressLint("NewApi")
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			tabHost=this.getTabHost();
			tabHost.addTab(tabHost.newTabSpec("1").setIndicator("1").setContent(new Intent(this,Activity_earnings.class)));
			tabHost.addTab(tabHost.newTabSpec("2").setIndicator("2").setContent(new Intent(this,Activity_shop.class)));
			tabHost.addTab(tabHost.newTabSpec("3").setIndicator("3").setContent(new Intent(this,Activity_invitation.class)));
			tabHost.addTab(tabHost.newTabSpec("4").setIndicator("4").setContent(new Intent(this,Activity_more.class)));
			
			radioderGroup = (RadioGroup) findViewById(R.id.main_radio);
			radioderGroup.setOnCheckedChangeListener(this);
			radioderGroup.check(R.id.mainTabs_radio_earnings);//默认第一个按钮
			
			//Log.i("test", config.faint) ;
			
			//ExitApplication.getInstance().addActivity(this);
			IntentFilter myIntentFilter = new IntentFilter();  
		    myIntentFilter.addAction("shut_down");  
		     //注册广播        
		    registerReceiver(mBroadcastReceiver, myIntentFilter);
			
			KeyguardManager mKeyguardManager = null ;
			KeyguardManager.KeyguardLock mKeyguardLock = null ;
			mKeyguardManager = (KeyguardManager)this.getSystemService(Context.KEYGUARD_SERVICE);
			mKeyguardLock = mKeyguardManager.newKeyguardLock("zdLock 1"); 
			mKeyguardLock.disableKeyguard();
	
			Button btn1 = new Button(this);
			btn1.setText("----------------------");
			btn1.setId(1);
			
			btn1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//loginQQ("1103513011");	
					Toast.makeText(MainActivity.this, "GO to LockActivity!",
						     Toast.LENGTH_SHORT).show();
			        Intent intent = new Intent(MainActivity.this, LockActivity.class); 
			        startActivity(intent); 
				}  
	        }); 





			
			startService(new Intent(MainActivity.this, LockService.class));
			
			//sliderLayout.setMainHandler(mHandler);
		}

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch(checkedId){
			case R.id.mainTabs_radio_earnings:
				tabHost.setCurrentTabByTag("1");
				break;
			case R.id.mainTabs_radio_shop:
				tabHost.setCurrentTabByTag("2");
				break;
			case R.id.mainTabs_radio_invitation:
				tabHost.setCurrentTabByTag("3");
				break;
			case R.id.mainTabs_radio_more:
				tabHost.setCurrentTabByTag("4");
				break;
			}		
			
		}

		
	 
		private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver(){  
	        @Override  
	        public void onReceive(Context context, Intent intent) {  
	            String action = intent.getAction();  
	            if(action.equals("shut_down")){  
	                //Toast.makeText(Test.this, "处理action名字相对应的广播", 200);  
	            	finish();
	            }  
	        }  
	          
	    };

}
