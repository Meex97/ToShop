import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateProductsCustomerComponent } from './create-products-customer.component';

describe('CreateProductsCustomerComponent', () => {
  let component: CreateProductsCustomerComponent;
  let fixture: ComponentFixture<CreateProductsCustomerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateProductsCustomerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateProductsCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
