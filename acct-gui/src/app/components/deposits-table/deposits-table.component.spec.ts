import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DepositsTableComponent } from './deposits-table.component';

describe('DepositsTableComponent', () => {
  let component: DepositsTableComponent;
  let fixture: ComponentFixture<DepositsTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DepositsTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DepositsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
