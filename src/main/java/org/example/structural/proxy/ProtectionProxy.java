package org.example.structural.proxy;

interface Drivable {

  void drive();
}

class Car implements Drivable {

  protected Driver driver;

  public Car(Driver driver) {
    this.driver = driver;
  }

  @Override
  public void drive() {
    System.out.println("Car being driven");
  }
}

class Driver {

  public int age;

  public Driver(int age) {
    this.age = age;
  }
}

class CarProxy extends Car {

  public CarProxy(Driver driver) {
    super(driver);
  }

  @Override
  public void drive() {
    if (driver.age >= 16)
      super.drive();
    else
      System.out.println("Driver too young!");
  }
}

class ProtectionProxyDemo {

  public static void main(String[] args) {
    Drivable car = new CarProxy(new Driver(12)); // 22
    car.drive();
  }
}