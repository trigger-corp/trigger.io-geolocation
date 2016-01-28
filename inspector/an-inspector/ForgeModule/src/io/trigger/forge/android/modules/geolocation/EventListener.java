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
		if (checkPermissions()) {
			location = new SimpleLocation(ForgeApp.getActivity(), true, true);
		}
	}

	public static boolean checkPermissions() {
		return ContextCompat.checkSelfPermission(ForgeApp.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
			   ContextCompat.checkSelfPermission(ForgeApp.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
	}
	
	@Override
	public void onResume() {
		if (location != null) {
			location.beginUpdates();
		}
	}
	
	@Override
	public void onPause() {
		if (location != null) {
			location.endUpdates();
		}
	}
	
	public static SimpleLocation location;
}
