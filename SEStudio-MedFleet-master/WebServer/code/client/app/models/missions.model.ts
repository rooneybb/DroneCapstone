export interface Mission {
    _id: string,
    drone_id?: string,
    flight_paths?: any, // todo change to latlng[]
    tickets?: string[],
    __v?: number,
    is_a?: string;
}