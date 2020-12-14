import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountRecordComponent } from './account-record.component';

describe('AccountRecordComponent', () => {
  let component: AccountRecordComponent;
  let fixture: ComponentFixture<AccountRecordComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountRecordComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountRecordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
