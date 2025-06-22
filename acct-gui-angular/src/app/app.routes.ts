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
        component: MainComponent,
        data: {
            menuItem: {
                text: "Main page",
                imageRef: "menu-icons/home.png"
            }
        }
    },
    {
        path: 'user-information',
        component: UserInformationComponent,
        data: {
            menuItem: {
                text: "User information",
                imageRef: "menu-icons/user-information.png"
            }
        }
    },
    {
        path: 'dashboard',
        component: DashboardComponent,
        data: {
            menuItem: {
                text: "Dashboard",
                imageRef: "menu-icons/dashboard.png"
            }
        }
    },
    {
        path: 'workspaces',
        component: WorkspacesComponent,
        data: {
            menuItem: {
                text: "Workspaces",
                imageRef: "menu-icons/workspaces.png"
            }
        }
    },
    {
        path: 'accounts',
        component: AccountsComponent,
        data: {
            menuItem: {
                text: "Accounts",
                imageRef: "menu-icons/accounts.png"
            }
        }
    },
    {
        path: 'deposits',
        component: AccountsComponent,
        data: {
            menuItem: {
                text: "Deposits",
                imageRef: "menu-icons/deposits.png"
            }
        }
    },
    {
        path: 'currency-history',
        component: CurrencyHistoryComponent,
        data: {
            menuItem: {
                text: "Currency history",
                imageRef: "menu-icons/currency-history.png"
            }
        }
    },
    {
        path: 'catalog',
        component: CatalogComponent,
        data: {
            menuItem: {
                text: "Catalong",
                imageRef: "menu-icons/catalog.png"
            }
        }
    },
    {
        path: 'components-showcase',
        component: ShowcaseComponent
    },
];
