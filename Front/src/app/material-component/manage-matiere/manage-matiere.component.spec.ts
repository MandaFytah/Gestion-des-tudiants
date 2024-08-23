import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageMatiereComponent } from './manage-matiere.component';

describe('ManageMatiereComponent', () => {
  let component: ManageMatiereComponent;
  let fixture: ComponentFixture<ManageMatiereComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageMatiereComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageMatiereComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
