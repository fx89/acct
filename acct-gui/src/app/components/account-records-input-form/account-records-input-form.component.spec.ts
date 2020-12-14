import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountRecordsInputFormComponent } from './account-records-input-form.component';

describe('AccountRecordsInputFormComponent', () => {
  let component: AccountRecordsInputFormComponent;
  let fixture: ComponentFixture<AccountRecordsInputFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountRecordsInputFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountRecordsInputFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
