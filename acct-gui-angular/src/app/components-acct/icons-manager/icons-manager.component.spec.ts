import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconsManagerComponent } from './icons-manager.component';

describe('IconsManagerComponent', () => {
  let component: IconsManagerComponent;
  let fixture: ComponentFixture<IconsManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconsManagerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconsManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
