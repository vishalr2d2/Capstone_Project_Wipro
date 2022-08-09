import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagePlaceRequestComponent } from './manage-place-request.component';

describe('ManagePlaceRequestComponent', () => {
  let component: ManagePlaceRequestComponent;
  let fixture: ComponentFixture<ManagePlaceRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManagePlaceRequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagePlaceRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
