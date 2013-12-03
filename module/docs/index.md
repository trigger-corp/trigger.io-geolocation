``geolocation``: Geolocation
============================

The ``forge.geolocation`` namespace allows you to access fine location data.

Although geolocation APIs are part of the HTML5 specification, on some
platforms, the default permissions dialogs can be cumbersome. So we offer an alternative way to get geolocation data.

##API

!method: forge.geolocation.getCurrentPosition(options, success, error)
!param: options `object` request specific levels of service from the location provider, currently supports ``"enableHighAccuracy": true`` to request GPS location if available
!param: success `function(position)` callback with an object matching the [W3C Position specification](http://dev.w3.org/geo/api/spec-source.html#coordinates)
!description: Get the user's current position.
!platforms: iOS, Android
!param: error `function(content)` called when the user chooses not to share their location with your app


> ::Note:: To enable easy porting from existing HTML5 code onto Forge, we also
accept parameters in the order ``(success, error, options)``

##Permissions

On Android this module will add the ``ACCESS_FINE_LOCATION`` permission
to your app, users will be prompted to accept this when they install
your app.
