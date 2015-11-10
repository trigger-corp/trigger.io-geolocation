package io.trigger.forge.android.modules.geolocation;

import io.trigger.forge.android.core.ForgeTask;

import com.google.gson.JsonObject;


public class API {
	public static void getCurrentPosition(final ForgeTask task) {

		// parse parameters
		boolean enableHighAccuracy = true;
		if (task.params.has("enableHighAccuracy")) {
			enableHighAccuracy = task.params.get("enableHighAccuracy").getAsBoolean();
		}
				
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
