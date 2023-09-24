package org.example.solid.dip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.javatuples.Triplet;

// A. High-level modules should not depend on low-level modules.
// Both should depend on abstractions.

// B. Abstractions should not depend on details.
// Details should depend on abstractions.

enum Relationship {
  PARENT,
  CHILD,
  SIBLING
}

interface RelationshipBrowser {

  List<Person> findAllChildrenOf(String name);
}

class Person {

  public String name;
  // dob etc.


  public Person(String name) {
    this.name = name;
  }
}

class Relationships implements RelationshipBrowser {

  // Triplet class requires javatuples
  private List<Triplet<Person, Relationship, Person>> relations =
      new ArrayList<>();

  public List<Person> findAllChildrenOf(String name) {

    return relations.stream()
        .filter(x -> Objects.equals(x.getValue0().name, name)
            && x.getValue1() == Relationship.PARENT)
        .map(Triplet::getValue2)
        .collect(Collectors.toList());
  }

  public List<Triplet<Person, Relationship, Person>> getRelations() {
    return relations;
  }

  public void addParentAndChild(Person parent, Person child) {
    relations.add(new Triplet<>(parent, Relationship.PARENT, child));
    relations.add(new Triplet<>(child, Relationship.CHILD, parent));
  }
}

class Research {
/*

  ISSUE
  High-level modules depend on low-level modules
*/
/*
  public Research(Relationships relationships) {
    // high-level: find all of john's children
    List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
    relations.stream()
        .filter(x -> x.getValue0().name.equals("John")
            && x.getValue1() == Relationship.PARENT)
        .forEach(ch -> System.out.println("John has a child called " + ch.getValue2().name));
  }
*/

  /*
    SOLUTION
    If there is a better way to change the data type of the children field from List<Person> to a
    new data type, we can easily create a new abstraction for finding children with the new data type.
  */
  public Research(RelationshipBrowser browser) {
    List<Person> children = browser.findAllChildrenOf("John");
    for (Person child : children) {
      System.out.println("John has a child called " + child.name);
    }
  }
}

class DIPDemo {

  public static void main(String[] args) {
    Person parent = new Person("John");
    Person child1 = new Person("Chris");
    Person child2 = new Person("Matt");

    // low-level module
    Relationships relationships = new Relationships();
    relationships.addParentAndChild(parent, child1);
    relationships.addParentAndChild(parent, child2);

    new Research(relationships);
  }
}
