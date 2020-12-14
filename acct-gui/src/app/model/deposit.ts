import { Identifiable } from './identifiable';

export class Deposit extends Identifiable {
    sourceAccountId : Number;
    accountNumber : String;
    bankId : Number;
    startDate : Date;
    endDate : Date;
    value : Number;
    interestPercent : Number;
}