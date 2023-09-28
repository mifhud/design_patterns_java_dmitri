package org.example.creational.builder;

class PersonFacet {

  // address
  public String streetAddress, postcode, city;

  // employment
  public String companyName, position;
  public int annualIncome;

  @Override
  public String toString() {
    return "PersonFacet{" +
        "streetAddress='" + streetAddress + '\'' +
        ", postcode='" + postcode + '\'' +
        ", city='" + city + '\'' +
        ", companyName='" + companyName + '\'' +
        ", position='" + position + '\'' +
        ", annualIncome=" + annualIncome +
        '}';
  }
}

// builder facade
class PersonFacetBuilder {

  // the object we're going to build
  protected PersonFacet person = new PersonFacet(); // reference!

  public PersonFacetJobBuilder works() {
    return new PersonFacetJobBuilder(person);
  }

  public PersonFacetAddressBuilder lives() {
    return new PersonFacetAddressBuilder(person);
  }

  public PersonFacet build() {
    return person;
  }
}

class PersonFacetAddressBuilder extends PersonFacetBuilder {

  public PersonFacetAddressBuilder(PersonFacet person) {
    this.person = person;
  }

  public PersonFacetAddressBuilder at(String streetAddress) {
    person.streetAddress = streetAddress;
    return this;
  }

  public PersonFacetAddressBuilder withPostcode(String postcode) {
    person.postcode = postcode;
    return this;
  }

  public PersonFacetAddressBuilder in(String city) {
    person.city = city;
    return this;
  }
}

class PersonFacetJobBuilder extends PersonFacetBuilder {

  public PersonFacetJobBuilder(PersonFacet person) {
    this.person = person;
  }

  public PersonFacetJobBuilder at(String companyName) {
    person.companyName = companyName;
    return this;
  }

  public PersonFacetJobBuilder asA(String position) {
    person.position = position;
    return this;
  }

  public PersonFacetJobBuilder earning(int annualIncome) {
    person.annualIncome = annualIncome;
    return this;
  }
}

class BuilderFacetsDemo {

  public static void main(String[] args) {
    PersonFacetBuilder pb = new PersonFacetBuilder();
    PersonFacet person = pb
        .lives()
        .at("123 London Road")
        .in("London")
        .withPostcode("SW12BC")
        .works()
        .at("Fabrikam")
        .asA("Engineer")
        .earning(123000)
        .build();
    System.out.println(person);
  }
}