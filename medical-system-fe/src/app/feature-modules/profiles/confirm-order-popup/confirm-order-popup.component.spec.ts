import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmOrderPopupComponent } from './confirm-order-popup.component';

describe('ConfirmOrderPopupComponent', () => {
  let component: ConfirmOrderPopupComponent;
  let fixture: ComponentFixture<ConfirmOrderPopupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConfirmOrderPopupComponent]
    });
    fixture = TestBed.createComponent(ConfirmOrderPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
