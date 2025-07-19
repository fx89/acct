import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DataScrollerComponent } from './data-scroller.component';

describe('DataScrollerComponent', () => {
  let component: DataScrollerComponent;
  let fixture: ComponentFixture<DataScrollerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DataScrollerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DataScrollerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
