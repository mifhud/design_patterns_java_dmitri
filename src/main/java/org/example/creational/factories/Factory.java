package org.example.creational.factories;

enum CoordinateSystem {
  CARTESIAN,
  POLAR
}

class Point {

  // singleton field
  public static final Point ORIGIN = new Point(0, 0);
  private double x, y;

  protected Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  // steps to add a new system
  // 1. augment CoordinateSystem
  // 2. change ctor

  public Point(double a,
      double b, // names do not communicate intent
      CoordinateSystem cs) {
    switch (cs) {
      case CARTESIAN:
        this.x = a;
        this.y = b;
        break;
      case POLAR:
        this.x = a * Math.cos(b);
        this.y = a * Math.sin(b);
        break;
    }
  }

  // factory method
  public static Point newCartesianPoint(double x, double y) {
    return new Point(x, y);
  }

  public static Point newPolarPoint(double rho, double theta) {
    return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
  }

  public static class Factory {

    public static Point newCartesianPoint(double x, double y) {
      return new Point(x, y);
    }
  }
}

class PointFactory {

  public static Point newCartesianPoint(double x, double y) {
    return new Point(x, y);
  }
}

class FactoryDemo {

  public static void main(String[] args) {
    Point point = new Point(2, 3, CoordinateSystem.CARTESIAN);
    Point origin = Point.ORIGIN;

    Point pointFactoryMethod = Point.newPolarPoint(2, 3);

    Point point1 = Point.Factory.newCartesianPoint(1, 2);
  }
}