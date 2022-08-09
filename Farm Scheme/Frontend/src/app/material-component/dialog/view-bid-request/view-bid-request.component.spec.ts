import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewBidRequestComponent } from './view-bid-request.component';

describe('ViewBidRequestComponent', () => {
  let component: ViewBidRequestComponent;
  let fixture: ComponentFixture<ViewBidRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewBidRequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewBidRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
