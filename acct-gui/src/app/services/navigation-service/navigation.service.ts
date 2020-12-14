import { Injectable } from '@angular/core';
import { Navigation, NavigationSection } from 'src/app/reusable/navigation';

@Injectable({
  providedIn: 'root'
})
export class NavigationService {

    /* SETUP NAVIGATION HERE -------------------------------------------------------------------------------- */

    public APP_SECTION_MAIN           : number = 0;
    public APP_SECTION_DASHBOARD      : number = 1;
    public APP_SECTION_ACCOUNTS       : number = 2;
    public APP_SECTION_DEPOSITS       : number = 3;
    public APP_SECTION_EXCHANGE_RATES : number = 4;

    public navigation : Navigation = new Navigation()
                                            .withSectionNameAndIcon(this.APP_SECTION_MAIN           , "Lists Editor"  , "app-section-main-icon"           )
                                            .withSectionNameAndIcon(this.APP_SECTION_DASHBOARD      , "Dashboard"     , "app-section-dashboard-icon"      )
                                            .withSectionNameAndIcon(this.APP_SECTION_ACCOUNTS       , "Accounts"      , "app-section-accounts-icon"       )
                                            .withSectionNameAndIcon(this.APP_SECTION_DEPOSITS       , "Deposits"      , "app-section-deposits-icon"       )
                                            .withSectionNameAndIcon(this.APP_SECTION_EXCHANGE_RATES , "Exchange Rates", "app-section-exchange-rates-icon" )

                                            .startingAtSection(this.APP_SECTION_DASHBOARD)

                                            .poppingStepsFromHistoryOnSectionGoBack(this.APP_SECTION_MAIN, 0);
  /* ====================================================================================================== */

    // Cache the current section so it doesn't have to be looked up into the navigation class all the time
    public currentSection : NavigationSection = this.navigation.getCurrentSection();

    constructor() { }

    public getCurrentSection() : NavigationSection {
        return this.navigation.getCurrentSection();
    }

    public getSections() : NavigationSection[] {
        return this.navigation.getSections();
    }

    public navigateToSectionId(sectionId : number) {
        this.navigation.navigateToSectionId(sectionId);
        this.currentSection = this.navigation.getCurrentSection();
    }

    public navigateBack() {
        this.navigation.navigateBack();
        this.currentSection = this.navigation.getCurrentSection();
    }
}
