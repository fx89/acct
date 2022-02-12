import { Identifiable } from './identifiable';

export class Account extends Identifiable {
    public name : String;
    public foreignCurrencyAccount : boolean;
    public currencyId : Number;

    constructor(id : number, name : String, foreignCurrencyAccount : boolean, currencyId : Number) {
      super(id);
      this.name = name;
      this.foreignCurrencyAccount = foreignCurrencyAccount;
      this.currencyId = currencyId;
    }
}
