import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageBideRequestComponent } from './manage-bide-request.component';

describe('ManageBideRequestComponent', () => {
  let component: ManageBideRequestComponent;
  let fixture: ComponentFixture<ManageBideRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageBideRequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageBideRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
