import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomeOrExpenseCategorySelectComponent } from './income-or-expense-category-select.component';

describe('IncomeOrExpenseCategorySelectComponent', () => {
  let component: IncomeOrExpenseCategorySelectComponent;
  let fixture: ComponentFixture<IncomeOrExpenseCategorySelectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncomeOrExpenseCategorySelectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncomeOrExpenseCategorySelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
