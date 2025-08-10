import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountRecordsComponent } from './account-records.component';

describe('AccountRecordsComponent', () => {
  let component: AccountRecordsComponent;
  let fixture: ComponentFixture<AccountRecordsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccountRecordsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccountRecordsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
