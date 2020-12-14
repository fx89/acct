import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrenciesManagerComponent } from './currencies-manager.component';

describe('CurrenciesManagerComponent', () => {
  let component: CurrenciesManagerComponent;
  let fixture: ComponentFixture<CurrenciesManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrenciesManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrenciesManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
