
export class NavigationSection {
    public id               : number;
    public historySkipFlag  : number;
    public nBackSteps       : number;
    public name             : string;
    public iconCssClass     : string;

    constructor(id : number, name : string, historySkipFlag : number, nBackSteps : number, iconCssClass : string) {
        this.id               = id              ;
        this.historySkipFlag  = historySkipFlag ;
        this.nBackSteps       = nBackSteps      ;
        this.name             = name            ;
        this.iconCssClass     = iconCssClass    ;
    }
}

export class Navigation {
    private sections          : NavigationSection[] = [];
    private currentSectionId  : number              = 0 ;
    private history           : number[]            = [];

    constructor() {

    }

    public withSectionNameAndIcon(sectionId : number, sectionName : string, iconCssClass : string) : Navigation {
        var navSection : NavigationSection = new NavigationSection(sectionId, sectionName, null, null, iconCssClass);
        this.sections[sectionId] = navSection;
        return this;
    }

    public startingAtSection(sectionId : number) : Navigation {
        this.currentSectionId = sectionId;
        return this;
    }

    public skippingSectionIdFromHistory(sectionId : number) : Navigation {
        this.sections[sectionId].historySkipFlag = 1;
        return this;
    }

    public notSkippingSectionIdFromHistory(sectionId : number) : Navigation {
      this.sections[sectionId].historySkipFlag = 0;
        return this;
    }

    public poppingStepsFromHistoryOnSectionGoBack(sectionId : number, nStepsToPop : number) : Navigation {
        this.sections[sectionId].nBackSteps = nStepsToPop;
        return this;
    }

    public navigateToSectionId(sectionId : number) {
        this.putSectionIdIntoHistory(this.currentSectionId);
        this.currentSectionId = sectionId;
    }

    public navigateBack() {
        let nSectionsToPop = this.sections[this.currentSectionId].nBackSteps;
        if (nSectionsToPop == null || nSectionsToPop == undefined) {
            nSectionsToPop = 1;
        }

        for (var n = 0 ; n < nSectionsToPop ; n++) {
            if (this.history.length > 0) {
                this.currentSectionId = this.history.pop();
            }
        }
    }

    private putSectionIdIntoHistory(sectionId : number) {
        if (this.sections[sectionId].historySkipFlag == 1) {
            return;
        }

        this.history.push(sectionId);
    }

    public getCurrentSection() : NavigationSection {
        return this.sections[this.currentSectionId];
    }

    public getSections() : NavigationSection[] {
        return this.sections;
    }
}
