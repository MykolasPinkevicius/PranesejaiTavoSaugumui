function initMap() {
  var myVilnius = { lat: 54.687157, lng: 25.279652 };

  $.ajax({
    type: "GET",
    url: "http://158.129.224.89:8080/v1/disturbances",
    data: {},
    success: function (data) {

      console.log(data);
      for (var i = 0; i < data.length; i++) {
        let image = {
          scaledSize: new google.maps.Size(35, 35)
        };

        switch (data[i].status) {
          case "NEW":
            image.url = 'https://cdn2.iconfinder.com/data/icons/bitsies/128/FlagRed-512.png';
            break;
          case "INPROGRESS":
            image.url = 'https://cdn2.iconfinder.com/data/icons/bitsies/128/FlagYellow-512.png';
            break;
          case "DONE":
            image.url = "https://cdn2.iconfinder.com/data/icons/bitsies/128/FlagGreen-512.png";
        }
        var marker = new google.maps.Marker({
          map: map,
          position: new google.maps.LatLng(data[i].location.latitude, data[i].location.longitude),
          icon: image
        });
        clickMarker(marker, data[i].disturbanceId);
      }
    }

  })

  // Create a map object and specify the DOM element
  // for display.
  var map = new google.maps.Map(document.getElementById('map'), {
    center: new google.maps.LatLng(myVilnius),
    zoom: 12.5,
    gestureHandling: 'greedy',
    styles: [
      { elementType: 'geometry', stylers: [{ color: '#242f3e' }] },
      { elementType: 'labels.text.stroke', stylers: [{ color: '#242f3e' }] },
      { elementType: 'labels.text.fill', stylers: [{ color: '#746855' }] },
      {
        featureType: 'administrative.locality',
        elementType: 'labels.text.fill',
        stylers: [{ color: '#d59563' }]
      },
      {
        featureType: 'poi',
        elementType: 'labels.text.fill',
        stylers: [{ color: '#d59563' }]
      },
      {
        featureType: 'poi.park',
        elementType: 'geometry',
        stylers: [{ color: '#263c3f' }]
      },
      {
        featureType: 'poi.park',
        elementType: 'labels.text.fill',
        stylers: [{ color: '#6b9a76' }]
      },
      {
        featureType: 'road',
        elementType: 'geometry',
        stylers: [{ color: '#38414e' }]
      },
      {
        featureType: 'road',
        elementType: 'geometry.stroke',
        stylers: [{ color: '#212a37' }]
      },
      {
        featureType: 'road',
        elementType: 'labels.text.fill',
        stylers: [{ color: '#9ca5b3' }]
      },
      {
        featureType: 'road.highway',
        elementType: 'geometry',
        stylers: [{ color: '#746855' }]
      },
      {
        featureType: 'road.highway',
        elementType: 'geometry.stroke',
        stylers: [{ color: '#1f2835' }]
      },
      {
        featureType: 'road.highway',
        elementType: 'labels.text.fill',
        stylers: [{ color: '#f3d19c' }]
      },
      {
        featureType: 'transit',
        elementType: 'geometry',
        stylers: [{ color: '#2f3948' }]
      },
      {
        featureType: 'transit.station',
        elementType: 'labels.text.fill',
        stylers: [{ color: '#d59563' }]
      },
      {
        featureType: 'water',
        elementType: 'geometry',
        stylers: [{ color: '#17263c' }]
      },
      {
        featureType: 'water',
        elementType: 'labels.text.fill',
        stylers: [{ color: '#515c6d' }]
      },
      {
        featureType: 'water',
        elementType: 'labels.text.stroke',
        stylers: [{ color: '#17263c' }]
      }
    ]
      
  });
}


function clickMarker(marker, id,) {
  (function (marker, id) {
    // add click event
    var image = new Image();
    
    google.maps.event.addListener(marker, 'click', function () {
      // infowindow.open(map, this);
      console.log(map);
      $.ajax({
        type: "GET",
        url: "http://158.129.224.89:8080/v1/disturbances/" + id,
        data: {},
        success: function (data) {
          var name = document.getElementById('viewName');
          var msg = document.getElementById('viewMsg');
          let imageHTML = document.getElementById('viewImage');
          name.innerHTML = data.disturbanceType;
          msg.innerHTML = data.description;

          image.src = "data:image/png;base64," + data.reportImages[0].content;
          image.style = "width:200px; height:200px";
          imageHTML.innerHTML = '<img style="width:450px"; src="' + image.src + '" />';
          infowindow = new google.maps.InfoWindow({
            content: image,
            maxWidth: 400

          });
          infowindow.open(map, marker);
        }
      });
    });
  })(marker, id);
}

function hideAllInfoWindows(map) {
  markers.forEach(function (marker) {
    marker.infowindow.close(map, marker);
  });
}