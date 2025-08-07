import { TestBed } from '@angular/core/testing';

import { WorkspaceSelectorService } from './workspace-selector.service';

describe('WorkspaceSelectorService', () => {
  let service: WorkspaceSelectorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkspaceSelectorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
