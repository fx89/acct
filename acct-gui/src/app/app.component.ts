import { Component, ViewEncapsulation } from '@angular/core';
import { Navigation } from './reusable/navigation';
import { DomOps } from './reusable/dom.ops';
import { NavigationService } from './services/navigation-service/navigation.service';
import { environment } from 'src/environments/environment';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent {
    title = 'acct-gui';

    constructor(public navigation : NavigationService) {
        DomOps.getTagByTypeAndName("link", "customSkin")
                          .setAttribute("href", 'assets/skins/' + environment.skin + '/main.css');
    }

}
