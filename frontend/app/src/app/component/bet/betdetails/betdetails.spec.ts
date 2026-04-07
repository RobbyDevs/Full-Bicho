import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Betdetails } from './betdetails';

describe('Betdetails', () => {
  let component: Betdetails;
  let fixture: ComponentFixture<Betdetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Betdetails],
    }).compileComponents();

    fixture = TestBed.createComponent(Betdetails);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
