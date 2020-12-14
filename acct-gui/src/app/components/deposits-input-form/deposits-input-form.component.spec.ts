import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DepositsInputFormComponent } from './deposits-input-form.component';

describe('DepositsInputFormComponent', () => {
  let component: DepositsInputFormComponent;
  let fixture: ComponentFixture<DepositsInputFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DepositsInputFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DepositsInputFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
