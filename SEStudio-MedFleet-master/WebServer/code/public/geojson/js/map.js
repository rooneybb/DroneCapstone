var map;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 17,
        center: {
            lat: 41.870187,
            lng: -87.61828
        }
    });

    map.data.loadGeoJson('https://dl.dropboxusercontent.com/u/27333031/geo.json');
    map.data.setStyle(styleFeature);

}

function styleFeature(feature) {
    var shape = feature.getGeometry().getType();
    var isA = feature.getProperty('is_a');

    switch (isA) {
        case 'flight_path':
            return {
                strokeColor: 'red',
                strokeWeight: 4
            };
        case 'flight_patha':
            return {
                strokeColor: 'green',
                strokeWeight: 4
            };
        default:
            return {
                strokeColor: 'blue',
                strokeWeight: 4
            };
    }
}
