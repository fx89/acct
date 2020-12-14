import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SectionExchangeRatesComponent } from './section-exchange-rates.component';

describe('SectionExchangeRatesComponent', () => {
  let component: SectionExchangeRatesComponent;
  let fixture: ComponentFixture<SectionExchangeRatesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SectionExchangeRatesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SectionExchangeRatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
