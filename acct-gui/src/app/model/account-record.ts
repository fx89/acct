import { Identifiable } from './identifiable';

export class AccountRecord extends Identifiable {
    public accountId : Number;
    public date : Date;
    public incomeOrExpenseItemId : Number;
    public value : Number;
    public exchangeRate : Number;
}
