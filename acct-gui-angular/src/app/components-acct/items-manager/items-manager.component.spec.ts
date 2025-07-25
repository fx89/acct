import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemsManagerComponent } from './items-manager.component';

describe('ItemsManagerComponent', () => {
  let component: ItemsManagerComponent;
  let fixture: ComponentFixture<ItemsManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ItemsManagerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemsManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
