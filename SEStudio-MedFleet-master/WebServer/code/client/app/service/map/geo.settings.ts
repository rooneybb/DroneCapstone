/// this works for drone, tickets, and flight paths
export var GeoSettings: GeoJSON.FactoryOptions = {
    PointLat: 'longitude', 
    PointLng: 'latitude',
    LineString: 'flight_paths'
};