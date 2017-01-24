import { PointFacotry }     from './geo.point.factory';



// only method. Craetes and returns a geojson object; 
export function GeoJsonFactory(settings: GeoJSON.FactoryOptions, data: Object[]): GeoJSON.Feature[] {
    var features: GeoJSON.Feature[] = []
    // set the keys for the object. all unknow witys added to properites in object 
    var { PointLat: ptLatKey, PointLng: ptLngKey, MultiPoint: mpKey, LineString: lsKey, 
                                            MultiLineString: mlsKey, Polygon: polyKey} = settings;
    for (var shape of data) {
        var f: GeoJSON.Feature = { type: 'Feature', properties: [] }; // the new feeature
        var pt = new PointFacotry; // pt feature. needed bc pt lat and long two objects 
        for (var key in shape) {   // creats the feature. if key mathces shape field passed by settings 
            switch (key) {         // we set that as the goemetry. else we add the feilds to the porperties
                case ptLatKey:
                    var ptObj = pt.setlat(shape[key]);
                    if (ptObj != undefined) { f.geometry = { type: 'Point', coordinates: ptObj }; }
                    break;
                case ptLngKey:
                    var ptObj = pt.setlng(shape[key]);
                    if (ptObj != undefined) { f.geometry = { type: 'Point', coordinates: ptObj }; }
                    break;
                case mpKey: // shape is a multi point 
                    f.geometry = { type: "MultiPoint", coordinates: shape[key] };
                    break;
                case lsKey:// shape is a linestring 
                    f.geometry = { type: "LineString", coordinates: shape[key] };
                    break;
                case mlsKey:// shape is a multi line string  
                    f.geometry = { type: "MultiLineString", coordinates: shape[key] };
                    break;
                case polyKey: // shape is a polygon 
                    f.geometry = { type: "Polygon", coordinates: shape[key] };
                    break;
                case "_id": // id field 
                    f.id = shape[key]; 
                default:
                    f.properties[key] = shape[key];
                    break;
            } // end switch
        } // end key for loop
        features.push(f); // add the feature to the feature collection
    } // end shape loop
    return features; // return the feature collection
} // end of factory method
