package pl.sensilabs;

import lombok.Getter;

@Getter
public enum OrderStatus {
  CONFIRMED,          //utworzono
  PROCESSING,        //w trakcie
  AWAITING_PAYMENT, //oczekuje na zapłatę
  PAID,            //opłacone
  SHIPPED,        //wysłane
  CANCELED;      //anulowane
}
