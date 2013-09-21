package com.flurry.example.ad.mediated.all;


import android.location.Criteria;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.flurry.android.FlurryAgent;


public class MainActivity extends Activity {
	
	private final String kLogTag = "FlurryAdServingAPI_A";
	public static String apiKey;
		
	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		apiKey = getResources().getString(R.string.flurry_api_key);

		Button banner = (Button)findViewById(R.id.banner);
		Button interstitial = (Button)findViewById(R.id.takeover);
		
		banner.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent();
				intent.setClass(mContext, BannerAdsActivity.class);
				//intent.putExtra("apikey", apiKey);
				startActivity(intent);
			}
			
		});
		interstitial.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent();
				intent.setClass(mContext, InterstitialAdsActivity.class);
				//intent.putExtra("apikey", apiKey);
				startActivity(intent);
			}
			
		});
		;
		
	}
	        
        @Override
        public void onStart() {
            super.onStart();
            try {
            	FlurryAgent.onStartSession(this,apiKey);
            } catch (Exception e) {
            	Log.e(kLogTag, e.getMessage());
            }
        }

        
         
        @Override  
        public void onStop() {
            super.onStop();
         
            FlurryAgent.onEndSession(this);
        }
}
