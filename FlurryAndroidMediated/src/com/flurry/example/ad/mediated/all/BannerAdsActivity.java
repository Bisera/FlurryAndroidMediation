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

import com.flurry.android.FlurryAdType;
import com.flurry.android.FlurryAds;
import com.flurry.android.FlurryAdSize;
import com.flurry.android.FlurryAgent;
import com.flurry.android.FlurryAdListener;

public class BannerAdsActivity extends Activity implements FlurryAdListener {
	FrameLayout mBanner;
	private final String kLogTag = "FlurryAdServingAPI";
	public static String apiKey ;
	private String adSpace; 
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.banner_ads_activity);
		mBanner = (FrameLayout) findViewById(R.id.banner);
		mContext = BannerAdsActivity.this;
		apiKey = getResources().getString(R.string.flurry_api_key);
		adSpace = getResources().getString(R.string.adSpaceName);
	

	}

	@Override
	public void onStart() {
		super.onStart();
		try {

			FlurryAgent.setLogEnabled(true);
			FlurryAgent.setLogLevel(2);
			FlurryAgent.onStartSession(mContext, apiKey);
			FlurryAds.setAdListener(this);
			FlurryAds.enableTestAds(false);
			
			FlurryAds.fetchAd(mContext, adSpace, mBanner,
					FlurryAdSize.BANNER_BOTTOM);

		} catch (Exception e) {
			Log.e(kLogTag, e.getMessage());
		}
	}
    
	@Override
	public void spaceDidReceiveAd(String adSpace) {
		// called when the ad has been prepared, ad can be displayed:
		Log.d(kLogTag, "spaceDidReceiveAd( " + adSpace + " )");

		FlurryAds.displayAd(mContext, adSpace, mBanner);
		Toast toast = Toast.makeText(mContext, "Displaying Ad", Toast.LENGTH_SHORT);

		toast.show();
	}

	@Override
	public void onStop() {
		super.onStop();
		FlurryAds.removeAd(this, adSpace, mBanner);
		FlurryAds.setAdListener(null);
		FlurryAgent.onEndSession(this);
	}

	@Override
	public void onAdClicked(String adSpace) {
		Log.d(kLogTag, "onAdClicked( " + adSpace + " )");

	}

	@Override
	public void onAdClosed(String adSpace) {
		Log.d(kLogTag, "onAdClosed( " + adSpace + " )");
	}

	@Override
	public void onAdOpened(String adSpace) {
		Log.d(kLogTag, "onAdOpened( " + adSpace + " )");

	}

	@Override
	public void onApplicationExit(String adSpace) {
		Log.d(kLogTag, "onApplicationExit( " + adSpace + " )");

	}

	@Override
	public void onRenderFailed(String adSpace) {
		Log.d(kLogTag, "onRenderFailed( " + adSpace + " )");
		Toast toast = Toast.makeText(mContext, "onRenderFailed",
				Toast.LENGTH_SHORT);
		toast.show();
	}

	@Override
	public void onVideoCompleted(String adSpace) {
		Log.d(kLogTag, "onVideoCompleted( " + adSpace + " )");

	}

	@Override
	public boolean shouldDisplayAd(String adSpace, FlurryAdType arg1) {
		Log.d(kLogTag, "shouldDisplayAd( " + adSpace + ", " + arg1 + " )");
		return true;
	}

	@Override
	public void spaceDidFailToReceiveAd(String adSpace) {
		Log.d(kLogTag, "spaceDidFailToReceiveAd(" + adSpace + " )");
		Toast toast = Toast.makeText(mContext, "spaceDidFailToReceiveAd",
				Toast.LENGTH_SHORT);
		toast.show();
	}

	@Override
	public void onRendered(String adSpace) {
		Log.d(kLogTag, "onRendered(" + adSpace + " )");
		
		
	}

}
