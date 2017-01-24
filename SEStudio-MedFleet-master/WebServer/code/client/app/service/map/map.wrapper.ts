import { Injectable } from '@angular/core';

// functions / settings 
import { GeoJsonFactory }   from './geo.factory';
import { GMapAPILoader }    from './map.service';
import { GeoSettings }      from './geo.settings';
import { styleFeature }     from './map.options';
// Models 
import { TicketModel }  from '../../models/ticket.model';
import { DroneModel }   from '../../models/drone.model';
import { Mission }      from '../../models/missions.model';

@Injectable()
export class MapFactory {
    constructor(private _map: GMapAPILoader) { }

    AddGeoJson(features: GeoJSON.Feature[]) {
        if (features.length > 0) {
            /// create the geojson object and add the features 
            var geojson: GeoJSON.FeatureCollection = {
                type: "FeatureCollection",
                    features: features 
            };  
            // add geojson to map
            this._map.getMap().then((map: google.maps.Map) => {
                map.data.addGeoJson(geojson);
                map.data.setStyle(styleFeature);
            });
        }
    }

    flightPathsToGeo(missions: Mission[]): void {
        /// craete a geojson object and pass it to update the map
        this.AddGeoJson(GeoJsonFactory(GeoSettings, missions));
    }

    DronesToGeo(drones: DroneModel[]): void {
        /// craete a geojson object and pass it to update the map
        this.AddGeoJson(GeoJsonFactory(GeoSettings, drones));
    }

    TicketsToGeo(tickets: TicketModel[]): void {
        /// craete a geojson object and pass it to update the map
        this.AddGeoJson(GeoJsonFactory(GeoSettings, tickets));
    }
}
