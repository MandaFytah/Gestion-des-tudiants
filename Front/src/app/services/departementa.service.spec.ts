import { TestBed } from '@angular/core/testing';

import { DepartementaService } from './departementa.service';

describe('DepartementaService', () => {
  let service: DepartementaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DepartementaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
