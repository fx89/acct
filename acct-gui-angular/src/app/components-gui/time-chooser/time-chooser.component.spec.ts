import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TimeChooserComponent } from './time-chooser.component';

describe('TimeChooserComponent', () => {
  let component: TimeChooserComponent;
  let fixture: ComponentFixture<TimeChooserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TimeChooserComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TimeChooserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
