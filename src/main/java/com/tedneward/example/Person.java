package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  protected static int count = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    count++;
    ssn = "";
  }

  public int getAge() {
    return age;
  }
  public void setAge(int a) throws IllegalArgumentException{
    if(a<0)
      throw new IllegalArgumentException("Age too low, was not born yet");
    age = a;
  }
  
  public String getName() {
    return name;
  }
  public void setName(String n) throws IllegalArgumentException {
    if(n==null)
      throw new IllegalArgumentException("No Nameless allowed here");
    name = n;
  }
  
  public double getSalary() {
    return salary;
  }
  public void setSalary(double s) {
    salary = s;
  }
  
  public String getSSN() {
    return ssn;
  }
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }

  @Override
   public boolean equals(Object other) {
     if(other==null || !(other instanceof Person))
       return false;
    if(name.equals(((Person)other).getName()) && age == ((Person)other).getAge()){
      return true;
    } else{
      return false;
    }
    //return (this.name.equals(other.getName()) && this.age == other.getAge());
  }

  public int count(){
    return count;
  }
  @Override
  public String toString() {
    return "[Person name:"+name+" age:"+age+" salary:"+salary+"]";
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
  //Create a static method "getNewardFamily" that returns an ArrayList<Person> consisting of four
  // Person objects: Ted, age 41, salary 250000; Charlotte, age 43, salary 150000; Michael, age 22,
  // salary 10000; and Matthew, age 15, salary 0.

  public static ArrayList<Person> getNewardFamily(){
    ArrayList<Person> temp = new ArrayList<Person>();
    temp.add(new Person("Ted",41,250000));
    temp.add(new Person("Charlotte",43,150000));
    temp.add(new Person("Michael",22,10000));
    temp.add(new Person("Matthew",15,0));
    return temp;
  }
  @Override
  public int compareTo(Object anotherPerson) throws ClassCastException {
    if (!(anotherPerson instanceof Person))
      throw new ClassCastException("A Person object expected.");
    double otherSalary = ((Person) anotherPerson).getSalary();
    return (int) (otherSalary - this.salary);
  }

  public static class AgeComparator implements Comparator{
    public boolean equals(Object one){
      if (!(one instanceof Person))
        throw new ClassCastException("A Person object expected.");
      if(this.equals(one))
        return true;
      else
        return false;
    }


    public int compare(Object one,Object two){
      if (!(one instanceof Person)||!(two instanceof Person))
        throw new ClassCastException("A Person object expected.");
      return ((Person)one).getAge() - ((Person)two).getAge();
    }
  }
}
