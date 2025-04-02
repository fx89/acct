import { Routes } from '@angular/router';
import { MainComponent } from './pages/main/main.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { WorkspacesComponent } from './pages/workspaces/workspaces.component';
import { AccountsComponent } from './pages/accounts/accounts.component';
import { CurrencyHistoryComponent } from './pages/currency-history/currency-history.component';
import { UserInformationComponent } from './pages/user-information/user-information.component';
import { CatalogComponent } from './pages/catalog/catalog.component';
import { ShowcaseComponent } from './components-gui/showcase/showcase.component';

export const routes: Routes = [
    {
        path: 'main',
        component: MainComponent
    },
    {
        path: 'dashboard',
        component: DashboardComponent
    },
    {
        path: 'workspaces',
        component: WorkspacesComponent
    },
    {
        path: 'accounts',
        component: AccountsComponent
    },
    {
        path: 'currency-history',
        component: CurrencyHistoryComponent
    },
    {
        path: 'catalog',
        component: CatalogComponent
    },
    {
        path: 'user-information',
        component: UserInformationComponent
    },
    {
        path: 'components-showcase',
        component: ShowcaseComponent
    },
];
