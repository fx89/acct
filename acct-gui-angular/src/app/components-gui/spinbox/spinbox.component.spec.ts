import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpinboxComponent } from './spinbox.component';

describe('SpinboxComponent', () => {
  let component: SpinboxComponent;
  let fixture: ComponentFixture<SpinboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpinboxComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpinboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
