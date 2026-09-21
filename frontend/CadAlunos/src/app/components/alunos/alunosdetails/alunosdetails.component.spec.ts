import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlunosdetailsComponent } from './alunosdetails.component';

describe('AlunosdetailsComponent', () => {
  let component: AlunosdetailsComponent;
  let fixture: ComponentFixture<AlunosdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlunosdetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlunosdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
