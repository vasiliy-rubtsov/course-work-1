import java.util.Objects;

public class Employee {
    public static int amount = 0; // Счетчик для генерации ID
    private int id; // ID сотрудника
    private String name; // Имя, отчество
    private String surname; // Фамилия
    private int department; // ID отдела (1 - 5)
    private int salary; // зарплата сотрудника

    public Employee(String name, String surname, int department, int salary) {
        id = ++amount;
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        if (department < 1 || department > 5) {
            throw new IllegalArgumentException("ID отдела должен быть в диапазоне [1 - 5]");
        }
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Зарплата сотрудника должна быть больше нуля");
        }
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return id == employee.id && department == employee.department && salary == employee.salary && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, department, salary);
    }

    @Override
    public String toString() {
        return "id=" + id + ": " +
                surname + " " +
                name + ", " +
                "отдел: " + department + ", " +
                "зарплата: " + salary;
    }
}
