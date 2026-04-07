import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Drawdetails } from './drawdetails';

describe('Drawdetails', () => {
  let component: Drawdetails;
  let fixture: ComponentFixture<Drawdetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Drawdetails],
    }).compileComponents();

    fixture = TestBed.createComponent(Drawdetails);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
