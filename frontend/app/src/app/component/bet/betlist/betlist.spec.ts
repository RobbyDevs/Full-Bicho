import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Betlist } from './betlist';

describe('Betlist', () => {
  let component: Betlist;
  let fixture: ComponentFixture<Betlist>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Betlist],
    }).compileComponents();

    fixture = TestBed.createComponent(Betlist);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
