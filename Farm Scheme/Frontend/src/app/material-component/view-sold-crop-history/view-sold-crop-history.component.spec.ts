import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewSoldCropHistoryComponent } from './view-sold-crop-history.component';

describe('ViewSoldCropHistoryComponent', () => {
  let component: ViewSoldCropHistoryComponent;
  let fixture: ComponentFixture<ViewSoldCropHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewSoldCropHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewSoldCropHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
