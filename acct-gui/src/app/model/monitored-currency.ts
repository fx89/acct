import { Identifiable } from './identifiable';

export class MonitoredCurrency extends Identifiable {
    public currencyTypeName : String;
    public lastCollectedDate : Date;
    public lastCollectedValue : Number;
    public bankId : number;
}