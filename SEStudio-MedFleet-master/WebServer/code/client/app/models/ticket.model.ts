export interface TicketModel {
    
    latitude: number;
    longitude:  number;
    altitude:   number;
    urgency:    number;
    status:     string;
    received:   string;
    completed?:  string;

}