import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticularsInsureeComponent } from './particulars-insuree.component';

describe('ParticularsInsureeComponent', () => {
  let component: ParticularsInsureeComponent;
  let fixture: ComponentFixture<ParticularsInsureeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParticularsInsureeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParticularsInsureeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
