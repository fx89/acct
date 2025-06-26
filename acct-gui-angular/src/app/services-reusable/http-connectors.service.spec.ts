import { TestBed } from '@angular/core/testing';

import { HttpConnectorsService } from './http-connectors.service';

describe('HttpConnectorsService', () => {
  let service: HttpConnectorsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpConnectorsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
