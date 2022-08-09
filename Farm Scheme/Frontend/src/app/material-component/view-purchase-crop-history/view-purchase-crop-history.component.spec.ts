import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPurchaseCropHistoryComponent } from './view-purchase-crop-history.component';

describe('ViewPurchaseCropHistoryComponent', () => {
  let component: ViewPurchaseCropHistoryComponent;
  let fixture: ComponentFixture<ViewPurchaseCropHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPurchaseCropHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPurchaseCropHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
