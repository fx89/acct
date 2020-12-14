export class MonthlyBalanceReportRecord {
    public recordYearMonth : number;
    public startingBalance : number;
    public incomingBalance : number;
    public outgoingBalance : number;
    public remainingBalance : number;

    constructor(recordYearMonth:number, startingBalance:number, incomingBalance:number, outgoingBalance:number) {
        this.recordYearMonth = recordYearMonth;
        this.startingBalance = startingBalance;
        this.incomingBalance = incomingBalance;
        this.outgoingBalance = outgoingBalance;
        this.remainingBalance = this.startingBalance + this.incomingBalance + this.outgoingBalance;
    }
}
