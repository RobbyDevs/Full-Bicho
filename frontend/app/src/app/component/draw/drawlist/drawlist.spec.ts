import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Drawlist } from './drawlist';

describe('Drawlist', () => {
  let component: Drawlist;
  let fixture: ComponentFixture<Drawlist>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Drawlist],
    }).compileComponents();

    fixture = TestBed.createComponent(Drawlist);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
