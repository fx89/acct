import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoneyTransferFormComponent } from './money-transfer-form.component';

describe('MoneyTransferFormComponent', () => {
  let component: MoneyTransferFormComponent;
  let fixture: ComponentFixture<MoneyTransferFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoneyTransferFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoneyTransferFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
