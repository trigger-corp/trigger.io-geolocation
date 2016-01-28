package io.trigger.forge.android.modules.geolocation;

import android.Manifest;

import im.delight.android.location.SimpleLocation;
import io.trigger.forge.android.core.ForgeActivity.EventAccessBlock;
import io.trigger.forge.android.core.ForgeApp;
import io.trigger.forge.android.core.ForgeTask;

import com.google.gson.JsonObject;


public class API {
	public static void getCurrentPosition(final ForgeTask task) {
		ForgeApp.getActivity().requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, new EventAccessBlock() {
			@Override
			public void run(boolean granted) {
				if (!granted) {
					task.error("Permission denied. User didn't grant access to location.", "EXPECTED_FAILURE", null);
					return;
				}

				// start location library if needed
				if (EventListener.location == null) {
					EventListener.location = new SimpleLocation(ForgeApp.getActivity(), true, true);
				}

				// check that location services are enabled
				if (!EventListener.location.hasLocationEnabled()) {
					task.error("Location services are disabled.", "EXPECTED_FAILURE", null);
					return;
				}

				// parse parameters
				boolean enableHighAccuracy = true;
				if (task.params.has("enableHighAccuracy")) {
					enableHighAccuracy = task.params.get("enableHighAccuracy").getAsBoolean();
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
		});
	}
}
