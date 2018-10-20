function initMap() {
    var myVilnius = {lat: 54.687157, lng: 25.279652};
    // var myMarkers;
    // $.ajax({
    //     type: "POST",
    //     url: "linkas",
    //     data: { data: JSON.stringify("getCord")},
    //     success: function(data){
    //         myMarkers = $data;
    //     }
    // })
    var myMarkers = [
        {lat: 54.600000, lng: 25.200000},
        {lat: 54.600000, lng: 25.230000},
        {lat: 54.600000, lng: 25.260000},
        {lat: 54.610000, lng: 25.260000},
        {lat: 54.620000, lng: 25.260000},
    ];

    // Create a map object and specify the DOM element
    // for display.
    var map = new google.maps.Map(document.getElementById('map'), {
      center: new google.maps.LatLng(myVilnius),
      zoom: 12
    });

    // Create a marker and set its position.
    var markers = [];
    for (var i = 0; i < myMarkers.length; ++i) {
        var a = Math.random()*100;
        var marker = new google.maps.Marker({
            map: map,
            position: new google.maps.LatLng(myMarkers[i])
        });

        (function(marker, i) {
            // add click event
            google.maps.event.addListener(marker, 'click', function() {
                $.ajax({
                    type: "POST",
                    url: "linkas",
                    data: { id: JSON.stringify('id') },
                    success: function(data){
                        var name = document.getElementById('viewName');
                        var msg = document.getElementById('viewMsg');
                        name.innerHTML = 'aaaaaaa';
                        msg.innerHTML = 'adadada';
                        
                    }
                });
                var name = document.getElementById('viewName');
                var msg = document.getElementById('viewMsg');
                console.log('aaaaaaaa');
                name.innerHTML = 'Testasss';
                msg.innerHTML = 'adadada';
                // infowindow = new google.maps.InfoWindow({
                //     content: 'Hello, World!!'
                // });
                // infowindow.open(map, marker);
            });
        })(marker, i);
    }
}
