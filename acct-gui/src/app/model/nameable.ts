import { Identifiable } from './identifiable';

export class Nameable extends Identifiable {
    public name : String;

    constructor(id : Number, name : String) {
        super(id);
        this.name = name;
    }
}
