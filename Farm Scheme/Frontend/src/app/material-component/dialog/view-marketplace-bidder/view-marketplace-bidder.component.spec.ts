import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewMarketplaceBidderComponent } from './view-marketplace-bidder.component';

describe('ViewMarketplaceBidderComponent', () => {
  let component: ViewMarketplaceBidderComponent;
  let fixture: ComponentFixture<ViewMarketplaceBidderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewMarketplaceBidderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewMarketplaceBidderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
