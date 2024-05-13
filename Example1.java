package Streams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

class Employee{
    private String name;

    private LocalDate hireDate;

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Employee(String name, LocalDate hireDate){
        this.name = name;
        this.hireDate = hireDate;
    }
}

public class Example1 {
    public static void main(String args[]){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", LocalDate.of(2000,1,15)));
        employees.add(new Employee("Bob", LocalDate.of(1995, 5, 10)));
        employees.add(new Employee("Charlie", LocalDate.of(1998, 8, 20)));
        employees.add(new Employee("David", LocalDate.of(2005, 3, 25)));

        //Using Java streams to find the most senior employee
        Employee mostSenior = employees.stream().min(Comparator.comparing(Employee::getHireDate))
                .orElseThrow(NoSuchElementException::new);

        System.out.println("Most Senior employee: "+mostSenior.getName());
    }




}
