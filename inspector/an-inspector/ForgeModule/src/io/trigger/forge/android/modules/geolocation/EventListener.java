package io.trigger.forge.android.modules.geolocation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import im.delight.android.location.SimpleLocation;
import io.trigger.forge.android.core.ForgeApp;
import io.trigger.forge.android.core.ForgeEventListener;

public class EventListener extends ForgeEventListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (!checkPermissions()) {
			ActivityCompat.requestPermissions(ForgeApp.getActivity(), new String[] {  
				Manifest.permission.ACCESS_FINE_LOCATION,
				Manifest.permission.ACCESS_COARSE_LOCATION
			}, PERMISSIONS_REQUEST);
		}
		
		location = new SimpleLocation(ForgeApp.getActivity(), true, true);
	}

	public static boolean checkPermissions() {
		return ContextCompat.checkSelfPermission(ForgeApp.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
			   ContextCompat.checkSelfPermission(ForgeApp.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
	}
	
	@Override
	public void onResume() {
		location.beginUpdates();
	}
	
	@Override
	public void onPause() {
		location.endUpdates();
	}
	
	public static SimpleLocation location;
	static final int PERMISSIONS_REQUEST = 1;
}
