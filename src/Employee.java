import java.util.*;

public class Employee {

    String position, name, surname;
    double fixedSalary;

    Employee(String jobTitle) {
        this.position = jobTitle;
    }
    Employee(){
    }

    void setFixedSalary(double fixedSalary){
        this.fixedSalary = fixedSalary;
    }
    double getFixedSalary(){
        return fixedSalary;
    }

    void setPosition(String position) {
        this.position = position;
    }
    String getPosition() {
        return position;
    }

    void setName(String name) {
        this.name = name;
    }

    String getName(){
        return name;
    }
    void setSurname(String surname) {
        this.surname = surname;
    }
    String getSurname(String surname) {
        return surname;
    }

}

interface EmployeePosition{
    String getJobTitle();
    double calcSalary();
}

class Manager implements EmployeePosition{

    public String getJobTitle() {
        return "Marketing manager";
    }

    public double calcSalary() {
        double salary = Math.round(115000 + (Math.random()) * ((25) + 1));
        company.CompanyIncome += 0.95 * salary;
        return 0.05 * salary + 50000;
    }
}

class TopManager implements EmployeePosition{

    public String getJobTitle() {
        return "Chief manager";
    }

    public double calcSalary() {
        if (company.CompanyIncome > 10000000){
            return 1.5 * 100000 + 100000;
        } else{
            return 100000;
        }
    }
}

class Operator implements EmployeePosition{

    public String getJobTitle() {
        return "Operator";
    }

    public double calcSalary() {
        return 30000;
    }
}

class company{

    void hire (String position, Employee bind) {
        switch (position) {
            case "Manager":  bind.setFixedSalary(new Manager().calcSalary());
                bind.setPosition(new Manager().getJobTitle());
                break;
            case "Top Manager":  bind.setFixedSalary(new TopManager().calcSalary());
                bind.setPosition(new TopManager().getJobTitle());
                break;
            case "Operator":  bind.setFixedSalary(new Operator().calcSalary());
                bind.setPosition(new Operator().getJobTitle());
                break;
        }
    }
    void hireAll(String position, int amount, List<Employee> employees) {
        for (int i = 0; i < amount; i++) {
            switch (position) {
                case "Manager":
                    employees.add(i, new Employee(new Manager().getJobTitle()));
                    employees.get(i).setFixedSalary(new Manager().calcSalary());

                    break;
                case "Top Manager":
                    employees.add(i, new Employee(new TopManager().getJobTitle()));
                    employees.get(i).setFixedSalary(new TopManager().calcSalary());
                    break;
                case "Operator":
                    employees.add(i, new Employee(new Operator().getJobTitle()));
                    employees.get(i).setFixedSalary(new Operator().calcSalary());
                    break;
            }
        }
        System.out.println(amount + " employees hired.");
    }

    void fire(String name, List<Employee> employees){
        for(int i=0; i<employees.size(); i++) {
            if (name == employees.get(i).getName()){
                System.out.println(employees.get(i).getName() + " fired");
                employees.remove(i);
            }
        }
    }
    void fire(int amount, List<Employee> employees){
        for(int i=0; i<amount; i++) {
            System.out.println(employees.get(i).getPosition() + " fired");
            employees.remove(i);
        }
    }

    private void sortSalary(List<Employee> employees){
        for(int i=0; i < employees.size(); i++){
            for(int j=i; j < employees.size(); j++){
                if(employees.get(i).getFixedSalary() > employees.get(j).getFixedSalary()){
                    Collections.swap(employees, i, j);
                }
            }
        }
    }

    List<Employee> getLowestSalaryStaff(List<Employee> employees, int count){
        sortSalary(employees);
        if(employees.size() > count){
            for(int i=count; i<employees.size(); i++){
                employees.remove(i);
            }
        }
        return employees;
    }
    List<Employee> getTopSalaryStaff(List<Employee> employees, int count) {
        sortSalary(employees);
        if (employees.size() > count) {
            for (int i = employees.size() - count; i > 0; i--) {
                employees.remove(i);
            }
        }
        return employees;
    }

    public static double CompanyIncome;

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<Employee>();
        company comp = new company();

        comp.hireAll("Manager",80, employees);
        comp.hireAll("Operator", 180, employees);
        comp.hireAll("Top Manager", 10, employees);
        System.out.println(employees.size());
        for(int i=0; i<employees.size(); i++) {
            System.out.println(employees.get(i).getPosition() + " " + employees.get(i).getFixedSalary());
            System.out.println();
        }

    }
}