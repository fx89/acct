import { Component, OnInit } from '@angular/core';
import { NavigationService } from 'src/app/services/navigation-service/navigation.service';
import { trigger, state, transition, style, animate } from '@angular/animations';

@Component({
  selector: 'app-sliding-menu',
  templateUrl: './sliding-menu.component.html',
  styleUrls: ['./sliding-menu.component.css'],

  animations: [
    trigger('pullMenu', [
      state('up'  , style({top: '-67px'})),
      state('down', style({top:  '-2px'})),
      transition('down=>up', animate('300ms')),
      transition('up=>down', animate('100ms'))
    ]),
  ]
})
export class SlidingMenuComponent implements OnInit {

    constructor( public navigation : NavigationService ) { }

    currentState : string = 'up';

    ngOnInit() {
    }

    public onMouseOver() {
        this.currentState = 'down';
    }

    public onMouseOut() {
        this.currentState = 'up';
    }

}
