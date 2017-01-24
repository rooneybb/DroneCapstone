export var MapOptions: google.maps.MapOptions = {
    zoom: 16,
    center: {
        lat: 41.870187,
        lng: -87.61828
    }
};

export function styleFeature(feature: google.maps.Data.Feature): google.maps.Data.StyleOptions {
    var isA = feature.getProperty('is_a');
   /// console.log(isA);
    switch (isA) {
        case 'drone':
        var copter = '/public/media/copter.png';
            return {
                icon: copter
   
            };
        case 'ticket':
            var cross = '/public/media/red_cross.png'
            return {
                icon: cross
                
            };
        case 'mission':
            return {
                strokeColor: 'grey',
                strokeWeight: 2 
            };
        default:
            return {
                visible: true
            };
    }
};


/// style interfaces
        // export interface StyleOptions {
        //     clickable?: boolean;
        //     cursor?: string;
        //     draggable?: boolean;
        //     editable?: boolean;
        //     fillColor?: string;
        //     fillOpacity?: number;
        //     icon?: string|Icon|Symbol;
        //     shape?: MarkerShape;
        //     strokeColor?: string;
        //     strokeOpacity?: number;
        //     strokeWeight?: number;
        //     title?: string;
        //     visible?: boolean;
        //     zIndex?: number;
        // }

    // export interface PolylineOptions {
    //     clickable?: boolean;
    //     draggable?: boolean;
    //     editable?: boolean;
    //     geodesic?: boolean;
    //     icons?: IconSequence[];
    //     map?: Map;
    //     path?: MVCArray|LatLng[]|LatLngLiteral[]; // MVCArray<LatLng>|Array<LatLng|LatLngLiteral>
    //     strokeColor?: string;
    //     strokeOpacity?: number;
    //     strokeWeight?: number;
    //     visible?: boolean;
    //     zIndex?: number;
    // }
    
    //    export interface PolygonOptions {
    //     /** Indicates whether this Polygon handles mouse events. Defaults to true. */
    //     clickable?: boolean;
    //     /**
    //      * If set to true, the user can drag this shape over the map.
    //      * The geodesic property defines the mode of dragging. Defaults to false.
    //      */
    //     draggable?: boolean;
    //     /**
    //      * If set to true, the user can edit this shape by dragging the control points
    //      * shown at the vertices and on each segment. Defaults to false.
    //      */
    //     editable?: boolean;
    //     /** The fill color. All CSS3 colors are supported except for extended named colors. */
    //     fillColor?: string;
    //     /** The fill opacity between 0.0 and 1.0 */
    //     fillOpacity?: number;
    //     /**
    //      * When true, edges of the polygon are interpreted as geodesic and will follow
    //      * the curvature of the Earth. When false, edges of the polygon are rendered as
    //      * straight lines in screen space. Note that the shape of a geodesic polygon may
    //      * appear to change when dragged, as the dimensions are maintained relative to
    //      * the surface of the earth. Defaults to false.
    //      */
    //     geodesic?: boolean;
    //     /** Map on which to display Polygon. */
    //     map?: Map;
    //     /**
    //      * The ordered sequence of coordinates that designates a closed loop. Unlike
    //      * polylines, a polygon may consist of one or more paths. As a result, the paths
    //      * property may specify one or more arrays of LatLng coordinates. Paths are
    //      * closed automatically; do not repeat the first vertex of the path as the last
    //      * vertex. Simple polygons may be defined using a single array of LatLngs. More
    //      * complex polygons may specify an array of arrays. Any simple arrays are
    //      * converted into MVCArrays. Inserting or removing LatLngs from the MVCArray
    //      * will automatically update the polygon on the map.
    //      */
    //     paths?: any[]; // MVCArray<MVCArray<LatLng>>|MVCArray<LatLng>|Array<Array<LatLng|LatLngLiteral>>|Array<LatLng|LatLngLiteral>
    //     /**
    //      * The stroke color.
    //      * All CSS3 colors are supported except for extended named colors.
    //      */
    //     strokeColor?: string;
    //     /** The stroke opacity between 0.0 and 1.0 */
    //     strokeOpacity?: number;
    //     /**
    //      * The stroke position. Defaults to CENTER.
    //      * This property is not supported on Internet Explorer 8 and earlier.
    //      */
    //     strokePosition?: StrokePosition;
    //     /** The stroke width in pixels. */
    //     strokeWeight?: number;
    //     /** Whether this polygon is visible on the map. Defaults to true. */
    //     visible?: boolean;
    //     /** The zIndex compared to other polys. */
    //     zIndex?: number;
    // }
    
    // export interface RectangleOptions {
    //     bounds?: LatLngBounds;
    //     clickable?: boolean;
    //     draggable?: boolean;
    //     editable?: boolean;
    //     fillColor?: string;
    //     fillOpacity?: number;
    //     map?: Map;
    //     strokeColor?: string;
    //     strokeOpacity?: number;
    //     strokePosition?: StrokePosition;
    //     strokeWeight?: number;
    //     visible?: boolean;
    //     zIndex?: number;
    // }
    
    //     export interface CircleOptions {
    //     center?: LatLng;
    //     clickable?: boolean;
    //     draggable?: boolean;
    //     editable?: boolean;
    //     fillColor?: string;
    //     fillOpacity?: number;
    //     map?: Map;
    //     radius?: number;
    //     strokeColor?: string;
    //     strokeOpacity?: number;
    //     strokePosition?: StrokePosition;
    //     strokeWeight?: number;
    //     visible?: boolean;
    //     zIndex?: number;
    // }