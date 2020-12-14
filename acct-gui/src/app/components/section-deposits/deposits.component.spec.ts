import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DepositsSectionComponent } from './deposits.component';

describe('DepositsSectionComponent', () => {
  let component: DepositsSectionComponent;
  let fixture: ComponentFixture<DepositsSectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DepositsSectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DepositsSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
