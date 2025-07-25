import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpServicesConfig } from './config/http-services-config';
import { provideAcctAccessTokensRepository, provideAcctIconsRepository, provideAcctItemsRepository, provideAcctPrivilegesRepository, provideAcctUsersRepository, provideAcctWorkspacesRepository } from './config/repositories-config';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(),

    // HTTP client services
    provideHttpServicesConfig(),

    // Repositories
    provideAcctAccessTokensRepository(),
    provideAcctWorkspacesRepository(),
    provideAcctUsersRepository(),
    provideAcctPrivilegesRepository(),
    provideAcctIconsRepository(),
    provideAcctItemsRepository()
  ]
};

// https://www.tektutorialshub.com/angular/angular-providers/#factory-provider-usefactory
// https://chatgpt.com/c/6856e887-6bfc-8006-9304-0132dc09e841
// https://angular.dev/guide/di/dependency-injection-providers