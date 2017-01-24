import {Injectable, Optional, NgZone} from '@angular/core';

@Injectable()
export class GMapAPILoader {
  private _scriptLoadingPromise: Promise<void>;
          map: Promise<google.maps.Map>;
  private _mapResolver: (value?: google.maps.Map) => void;

  constructor(private _zone: NgZone) {
    this.map =
      new Promise<google.maps.Map>((resolve: () => void) => { this._mapResolver = resolve; });
  }

  getMap(): Promise<google.maps.Map>{
     // this.map.then(onfulfilled, value, onrejected, reason)
      return this.map;
  }
  createMap(el: HTMLElement, mapOptions: google.maps.MapOptions): Promise<void> {
    return this.load().then(() => {
      const map = new google.maps.Map(el, mapOptions);
      this._mapResolver(<google.maps.Map>map);
      return;
    });
  }

  load(): Promise<void> {
    if (this._scriptLoadingPromise) {
      return this._scriptLoadingPromise;
    }

    const script = document.createElement('script');
    script.type = 'text/javascript';
    script.async = true;
    script.defer = true;
    const callbackName: string = 'initMap';
    script.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyAbPi-tYfg2yibUTQL2Zwrp4-dXHE6iBG8&signed_in=true&callback=initMap';

    this._scriptLoadingPromise = new Promise<void>((resolve: Function, reject: Function) => {
      (<any>window)[callbackName] = () => { resolve(); };

      script.onerror = (error: Event) => { reject(error); };
    });

    document.body.appendChild(script);
    return this._scriptLoadingPromise;
  }
}
