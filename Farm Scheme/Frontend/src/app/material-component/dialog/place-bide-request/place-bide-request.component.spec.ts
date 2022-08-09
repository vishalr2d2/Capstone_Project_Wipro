import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlaceBideRequestComponent } from './place-bide-request.component';

describe('PlaceBideRequestComponent', () => {
  let component: PlaceBideRequestComponent;
  let fixture: ComponentFixture<PlaceBideRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlaceBideRequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlaceBideRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
