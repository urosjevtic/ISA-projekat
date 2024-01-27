import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContractSimulatorComponent } from './contract-simulator.component';

describe('ContractSimulatorComponent', () => {
  let component: ContractSimulatorComponent;
  let fixture: ComponentFixture<ContractSimulatorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ContractSimulatorComponent]
    });
    fixture = TestBed.createComponent(ContractSimulatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
