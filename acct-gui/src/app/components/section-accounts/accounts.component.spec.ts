import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountsSectionComponent } from './accounts.component';

describe('AccountsSectionComponent', () => {
  let component: AccountsSectionComponent;
  let fixture: ComponentFixture<AccountsSectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountsSectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountsSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
