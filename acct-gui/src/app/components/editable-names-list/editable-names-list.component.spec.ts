import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditableNamesListComponent } from './editable-names-list.component';

describe('EditableNamesListComponent', () => {
  let component: EditableNamesListComponent;
  let fixture: ComponentFixture<EditableNamesListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditableNamesListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditableNamesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
