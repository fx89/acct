import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomeOrExpenseItemsManagerComponent } from './income-or-expense-items-manager.component';

describe('IncomeOrExpenseItemsManagerComponent', () => {
  let component: IncomeOrExpenseItemsManagerComponent;
  let fixture: ComponentFixture<IncomeOrExpenseItemsManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncomeOrExpenseItemsManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncomeOrExpenseItemsManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
