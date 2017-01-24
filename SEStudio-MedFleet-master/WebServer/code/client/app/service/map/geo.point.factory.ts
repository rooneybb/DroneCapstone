export class PointFacotry {
    
    private _lat: number;
    private _lng: number;
    
    constructor() {}
    
    setlat (l: number): Object{
        this._lat = l;
        if (this._lng != undefined) {
            return [this._lat, this._lng]; }
        return undefined;
    }
    
    setlng (l: number): Object{
        this._lng = l;
        if (this._lat != undefined) {
            return [this._lat, this._lng]; }
        return undefined;
    }
}