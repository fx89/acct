import { Routes } from '@angular/router';
import { MainComponent } from './pages/main/main.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { WorkspacesComponent } from './pages/workspaces/workspaces.component';
import { AccountsComponent } from './pages/accounts/accounts.component';
import { CurrencyHistoryComponent } from './pages/currency-history/currency-history.component';
import { UserInformationComponent } from './pages/user-information/user-information.component';
import { CatalogComponent } from './pages/catalog/catalog.component';
import { ShowcaseComponent } from './components-gui/showcase/showcase.component';
import { LoginFormComponent } from './components-acct/login-form/login-form.component';
import { DepositsComponent } from './pages/deposits/deposits.component';
import { ReportsComponent } from './pages/reports/reports.component';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginFormComponent
    },
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
        path: 'reports',
        component: ReportsComponent,
        data: {
            menuItem: {
                text: "Reports",
                imageRef: "menu-icons/report.png"
            }
        }
    },
    {
        path: 'dashboards',
        component: DashboardComponent,
        data: {
            menuItem: {
                text: "Dashboards",
                imageRef: "menu-icons/dashboard.png",
                isWithinWorkspaceContext: true
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
                imageRef: "menu-icons/accounts.png",
                isWithinWorkspaceContext: true
            }
        }
    },
    {
        path: 'deposits',
        component: DepositsComponent,
        data: {
            menuItem: {
                text: "Deposits",
                imageRef: "menu-icons/deposits.png",
                isWithinWorkspaceContext: true
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
                text: "Catalog",
                imageRef: "menu-icons/catalog.png"
            }
        }
    },
    {
        path: 'components-showcase',
        component: ShowcaseComponent
    },
];
