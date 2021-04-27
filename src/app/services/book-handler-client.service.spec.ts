import { TestBed } from '@angular/core/testing';

import { BookHandlerClientService } from './book-handler-client.service';

describe('BookHandlerClientService', () => {
  let service: BookHandlerClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BookHandlerClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
