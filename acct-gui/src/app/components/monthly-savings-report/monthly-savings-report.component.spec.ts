import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthlySavingsReportComponent } from './monthly-savings-report.component';

describe('MonthlySavingsReportComponent', () => {
  let component: MonthlySavingsReportComponent;
  let fixture: ComponentFixture<MonthlySavingsReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MonthlySavingsReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonthlySavingsReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
