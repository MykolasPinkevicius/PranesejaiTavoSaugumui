function initMap() {
  var myVilnius = { lat: 54.687157, lng: 25.279652 };
  var infowindow = new google.maps.InfoWindow();

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
        clickMarker(marker, data[i].disturbanceId, infowindow);
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


function clickMarker(marker, id, infowindow) {
  (function (marker, id) {
    // add click event
    google.maps.event.addListener(marker, 'click', function () {
      var image = new Image();
      $.ajax({
        type: "GET",
        url: "http://158.129.224.89:8080/v1/disturbances/" + id,
        data: {},
        success: function (data) {
          image.src = "data:image/png;base64," + data.reportImages[0].content;

          let newDiv = document.createElement("div");

          let h3 = document.createElement("h2");
          h3.innerHTML = "Šiukšlės";
          h3.style.textAlign = "center"
          newDiv.appendChild(h3);

          let descriptionImage = document.createElement("div");
          descriptionImage.innerHTML = '<img style="width:300px; height:200px"; src="' + image.src + '" />';
          descriptionImage.style.marginLeft = "auto";
          descriptionImage.style.marginRight = "auto";
          descriptionImage.style.width = "100%";
          descriptionImage.style.display = "block";
          newDiv.appendChild(descriptionImage);

          let description = document.createElement("h4");
          description.style.maxWidth = "300px";
          description.innerHTML = data.description + " Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
          newDiv.appendChild(description);

          let buttonDiv = document.createElement("div");
          let confirmDeleteDiv = document.createElement("div");


          let confirmButton = document.createElement("input");
          confirmButton.type = "button";
          confirmButton.onclick = function () {
            $.ajax({
              type: "POST",
              url: "http://158.129.224.89:8080//v1/disturbances/" + id + "/status",
              dataType: 'json',
              contentType: "application/json",
              data: JSON.stringify({ status: "INPROGRESS" }),
              success: function (data) {
                console.log("Noretum");
              }
            })

            console.log('patvirtinti');
          }
          confirmButton.value = "Patvirtinti pranešimą";

          buttonDiv.appendChild(confirmButton);

          confirmButton = document.createElement("input");
          confirmButton.type = "button";
          confirmButton.onclick = function () {
            console.log("aaaaa");
          };
          confirmButton.value = "Ištrinti";

          buttonDiv.appendChild(confirmButton);

          confirmButton = document.createElement("input");
          confirmButton.type = "button";
          confirmButton.onclick = function () {
            console.log('donea');
          };
          confirmButton.value = "Problema Išspręsta";

          confirmDeleteDiv.appendChild(confirmButton);

          buttonDiv.appendChild(confirmDeleteDiv);

          newDiv.appendChild(buttonDiv);

          var imagetest = '<img style="width:200px; height:200px"; src="' + image.src + '" />';
          infowindow.setContent(newDiv);
          // infowindow.setContent();
          infowindow.open(map, marker);
        }
      });
    });
  })(marker, id);
}

// let confirmReport = (id) => {
//   $.ajax({
//     type: "POST",
//     url: "http://158.129.224.89:8080//v1/disturbances/"+id+"/status",
//     data: {
//       status: JSON.stringify("INPROGRESS")
//     },
//     success: function (data) {
//       console.log("Noretum");
//       }
//     })

//   }

