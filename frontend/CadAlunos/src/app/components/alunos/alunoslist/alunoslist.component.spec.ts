import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlunoslistComponent } from './alunoslist.component';

describe('AlunoslistComponent', () => {
  let component: AlunoslistComponent;
  let fixture: ComponentFixture<AlunoslistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlunoslistComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlunoslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
