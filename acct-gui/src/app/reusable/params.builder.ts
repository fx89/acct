export class ParamsBuilder {
    private params : Map<string, string> = new Map<string, string>();

    public withStringParam(key : string, value : String) : ParamsBuilder {
        this.params.set(key, value.valueOf());
        return this;
    }

    public withNumberParam(key : string, value : Number) {
        this.params.set(key, value.valueOf().toString());
        let n : number = 0;
        return this;
    }


    public build() : Map<string, string> {
        return this.params;
    }
}
