package org.example.solid.isp;

interface Machine {

  void print(Document d);

  void fax(Document d) throws Exception;

  void scan(Document d) throws Exception;
}

interface Printer {

  void Print(Document d) throws Exception;
}

interface IScanner {

  void Scan(Document d) throws Exception;
}

/*
SOLUTION
Interfaces should be split into smaller, more specific interfaces that clients can choose from
depending on their needs.
*/
interface MultiFunctionDevice extends Printer, IScanner //
{

}

class Document {

}

// ok if you need a multifunction machine
class MultiFunctionPrinter implements Machine {

  public void print(Document d) {
    //
  }

  public void fax(Document d) {
    //
  }

  public void scan(Document d) {
    //
  }
}

class OldFashionedPrinter implements Machine {

  public void print(Document d) {
    // yep
  }

  /*
    ISSUE
    The fax() function in the OldFashionedPrinter class raises an exception if the printer does not
    have the fax feature. This works but is not efficient.
  */
  public void fax(Document d) throws Exception {
    throw new Exception();
  }

  public void scan(Document d) throws Exception {
    throw new Exception();
  }
}

class JustAPrinter implements Printer {

  public void Print(Document d) {

  }
}

class Photocopier implements Printer, IScanner {

  public void Print(Document d) throws Exception {
    throw new Exception();
  }

  public void Scan(Document d) throws Exception {
    throw new Exception();
  }
}

class MultiFunctionMachine implements MultiFunctionDevice {

  // compose this out of several modules
  private Printer printer;
  private IScanner scanner;

  public MultiFunctionMachine(Printer printer, IScanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
  }

  public void Print(Document d) throws Exception {
    printer.Print(d);
  }

  public void Scan(Document d) throws Exception {
    scanner.Scan(d);
  }
}