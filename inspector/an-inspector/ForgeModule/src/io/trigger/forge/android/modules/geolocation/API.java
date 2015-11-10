package io.trigger.forge.android.modules.geolocation;

import im.delight.android.location.SimpleLocation;
import io.trigger.forge.android.core.ForgeApp;
import io.trigger.forge.android.core.ForgeParam;
import io.trigger.forge.android.core.ForgeTask;

import com.google.gson.JsonObject;


public class API {
	public static void getCurrentPosition(final ForgeTask task,
			@ForgeParam("timeout") final int timeout,
			@ForgeParam("maximumAge") final int maximumAge, 
			@ForgeParam("enableHighAccuracy") final boolean enableHighAccuracy) {
				
		// check permissions
		if (!EventListener.location.hasLocationEnabled() || !EventListener.checkPermissions()) {
			task.error("Permission denied", "EXPECTED_FAILURE", null);
			return;
		}
		
		// query location
		JsonObject coords = new JsonObject();
		coords.addProperty("latitude", EventListener.location.getLatitude());
		coords.addProperty("longitude", EventListener.location.getLongitude());
		coords.addProperty("altitude", EventListener.location.getAltitude());
		coords.addProperty("speed", EventListener.location.getSpeed());
		
		JsonObject result = new JsonObject();
		result.add("coords", coords);
		
		task.success(result);
	}
}
