import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomeOrExpenseItemsComponent } from './income-or-expense-items.component';

describe('IncomeOrExpenseItemsComponent', () => {
  let component: IncomeOrExpenseItemsComponent;
  let fixture: ComponentFixture<IncomeOrExpenseItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IncomeOrExpenseItemsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IncomeOrExpenseItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
