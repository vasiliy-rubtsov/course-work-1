public class EmployeeBook {
    private final Employee[] employees = new Employee[10];
    private void validateDepartmentId(int department) {
        if (department < 1 || department > 5) {
            throw new IllegalArgumentException("ID отдела должен быть в диапазоне [1 - 5]");
        }
    }

    /**
     * Добавление нового сотрудника
     * Очень сложно
     *
     * @param name String
     * @param surname String
     * @param department int
     * @param salary int
     * @return boolean
     */
    public boolean addEmployee(String name, String surname, int department, int salary) {
        Employee employee = new Employee(name, surname, department, salary);
        boolean result = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = employee;
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * Удаление сотрудника с указанным ID
     * Очень сложно
     *
     * @param id int
     */
    public void removeEmployeeById(int id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getId() == id) {
                employees[i] = null;
                break;
            }
        }
    }

    /**
     * Поиск сотрудника по указанному ID
     * Очень сложно
     *
     * @param id int
     * @return Employee
     */
    public Employee findEmployeeById(int id) {
        Employee result = null;
        for (Employee employee : employees) {
            if (employee != null && employee.getId() == id) {
                result = employee;
                break;
            }
        }
        return result;
    }

    /**
     * поиск сотрудника с максимальной ЗП по отделу
     * Повышенная сложность
     *
     * @param department int - ID отдела
     * @return Employee
     */
    public Employee findEmployeeWithMaxSalary(int department)
    {
        validateDepartmentId(department);
        Employee result = null;
        boolean isFirst = true;
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null || department != employee.getDepartment()) {
                continue;
            }

            // Устанавливаем значение результата, равным текущему
            if (
                 isFirst // Это первый непустой элемент, совпадающий по номеру отдела
                 || employee.getSalary() > result.getSalary() // зарплата больше, чем у установленного ранее
            ) {
                result = employee;
                isFirst = false; // Следующий элемент уже не будет первым
            }

        }
        return result;
    }

    /**
     * поиск сотрудника с максимальной ЗП по всей организации
     * Базовая сложность
     * @return Employee
     */
    public Employee findEmployeeWithMaxSalary() {
        Employee result = null;
        boolean isFirst = true;
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой
            if (employee == null) {
                continue;
            }

            // Устанавливаем значение результата, равным текущему
            if (
                    isFirst // Это первый непустой элемент, совпадающий по номеру отдела
                            || employee.getSalary() > result.getSalary() // зарплата больше, чем у установленного ранее
            ) {
                result = employee;
                isFirst = false; // Следующий элемент уже не будет первым
            }

        }
        return result;
    }

    /**
     * поиск сотрудника с минимальной ЗП по отделу
     * Повышенная сложность
     *
     * @param department int - ID отдела
     * @return Employee
     */
    public Employee findEmployeeWithMinSalary(int department) {
        validateDepartmentId(department);
        Employee result = null;
        boolean isFirst = true;
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null || department != employee.getDepartment()) {
                continue;
            }

            // Устанавливаем значение результата, равным текущему
            if (
                isFirst // Это первый непустой элемент, совпадающий по номеру отдела
                || employee.getSalary() < result.getSalary() // поиск по минимальной зарплате, и зарплата меньше, чем у установленного ранее
            ) {
                result = employee;
                isFirst = false; // Следующий элемент уже не будет первым
            }

        }
        return result;
    }

    /**
     * поиск сотрудника с минимальной ЗП по всей организации
     * Базовая сложность
     * @return Employee
     */
    public Employee findEmployeeWithMinSalary() {
        Employee result = null;
        boolean isFirst = true;
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null) {
                continue;
            }

            // Устанавливаем значение результата, равным текущему
            if (
                 isFirst // Это первый непустой элемент, совпадающий по номеру отдела
                 || employee.getSalary() < result.getSalary() // поиск по минимальной зарплате, и зарплата меньше, чем у установленного ранее
            ) {
                result = employee;
                isFirst = false; // Следующий элемент уже не будет первым
            }

        }
        return result;

    }

    /**
     * расчет фонда ЗП по отделу
     * Повышенная сложность
     *
     * @param department int - ID отдела
     * @return int
     */
    public int calculateTotalSalary(int department) {
        validateDepartmentId(department);
        int total = 0;
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null || department != employee.getDepartment()) {
                continue;
            }
            total += employee.getSalary();
        }
        return total;
    }

    /**
     * расчет фонда ЗП по всей организации
     * Базовая сложность
     *  @return int
     */
    public int calculateTotalSalary() {
        int total = 0;
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null) {
                continue;
            }
            total += employee.getSalary();
        }
        return total;
    }

    /**
     * расчет средней ЗП по отделу
     * Повышенная сложность
     *
     * @param department int
     * @return int
     */
    public int calculateAverageSalary(int department) {
        validateDepartmentId(department);
        int total = 0;
        int amount = 0;
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null || department != employee.getDepartment()) {
                continue;
            }
            total += employee.getSalary();
            amount++;
        }

        if (amount == 0) {
            return 0;
        }

        return total / amount;
    }

    /**
     * расчет средней ЗП по всей организации
     * Базовая сложность
     *
     * @return int
     */
    public int calculateAverageSalary() {
        int total = 0;
        int amount = 0;
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null) {
                continue;
            }
            total += employee.getSalary();
            amount++;
        }

        if (amount == 0) {
            return 0;
        }

        return total / amount;

    }

    /**
     * Индексация ЗП сотрудникам отдела
     * Повышенная сложность
     *
     * @param percent int - процент повышения ЗП
     * @param department int - ID отдела
     */
    public void salaryIndexation(int percent, int department) {
        validateDepartmentId(department);
        if (percent <= 0) {
            throw new IllegalArgumentException("Процент повышения зарплаты должен быть больше нуля");
        }
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null || department != employee.getDepartment()) {
                continue;
            }
            int salary = employee.getSalary();
            salary += salary * percent / 100;
            employee.setSalary(salary);
        }
    }

    /**
     * Индексация ЗП всем сотрудникам организации
     * Повышенная сложность
     *
     * @param percent int- процент повышения ЗП
     */
    public void salaryIndexation(int percent) {
//        _salaryIndexation(0, percent);
        if (percent <= 0) {
            throw new IllegalArgumentException("Процент повышения зарплаты должен быть больше нуля");
        }
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null) {
                continue;
            }
            int salary = employee.getSalary();
            salary += salary * percent / 100;
            employee.setSalary(salary);
        }
    }

    /**
     * Отчет по сотрудникам отдела
     * Повышенная сложность
     *
     * @param department int - ID отдела
     */
    public void employeesReport(int department) {
        String header = "Список сотрудников отдела " + department;
        String report = "";
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null || department != employee.getDepartment()) {
                continue;
            }

            report += "id = " + employee.getId() + ": " + employee.getSurname() + " " + employee.getName() + ", зарплата: " + employee.getSalary() + "\r\n";
        }

        if (report.isEmpty()) {
            report = "Ничего не найдено\r\n";
        }

        System.out.println(header);
        System.out.println("----------------------------------");
        System.out.print(report);
        System.out.println("----------------------------------");
    }

    /**
     * Отчет по сотрудникам организации в целом
     * Базовая сложность
     */
    public void employeesReport() {
        String header = "Список сотрудников организации";
        String report = "";
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null) {
                continue;
            }

            report += "id = " + employee.getId() + ": " + employee.getSurname() + " " + employee.getName() + ", зарплата: " + employee.getSalary() + "\r\n";
        }

        if (report.isEmpty()) {
            report = "Ничего не найдено\r\n";
        }

        System.out.println(header);
        System.out.println("----------------------------------");
        System.out.print(report);
        System.out.println("----------------------------------");
    }

    /**
     * Отчет по сотрудникам отдела c ЗП больше, чем
     * Повышенная сложность
     *
     * @param salary int - критерий отбора по ЗП
     * @param department int - ID отдела
     */
    public void employeesWithSalaryMoreThanReport(int salary, int department) {
        if (salary < 0 || salary == 0) {
            throw new IllegalArgumentException("Зарплата сотрудника должна быть больше нуля");
        }

        String header = "Список сотрудников отдела " + department
            + " c ЗП от " + salary + " руб.";

        String report = "";
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null || !(department == employee.getDepartment() || department == 0)) {
                continue;
            }

            // Пропускаем элемент, если ЗП не соответствует критериям отбора
            if (employee.getSalary() < salary) {
                continue;
            }

            report += "id = " + employee.getId() + ": " + employee.getSurname() + " " + employee.getName() + ", зарплата: " + employee.getSalary() + "\r\n";
        }

        if (report.isEmpty()) {
            report = "Ничего не найдено\r\n";
        }

        System.out.println(header);
        System.out.println("----------------------------------");
        System.out.print(report);
        System.out.println("----------------------------------");

    }

    /**
     * Отчет по сотрудникам организации в целом с ЗП больше, чем
     * Повышенная сложность
     *
     * @param salary int - критерий отбора по ЗП
     */
    public void employeesWithSalaryMoreThanReport(int salary) {
        if (salary < 0 || salary == 0) {
            throw new IllegalArgumentException("Зарплата сотрудника должна быть больше нуля");
        }

        String header = "Список сотрудников  организации "
            + " c ЗП от " + salary + " руб.";

        String report = "";
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null) {
                continue;
            }

            // Пропускаем элемент, если ЗП не соответствует критериям отбора
            if (employee.getSalary() < salary) {
                continue;
            }

            report += "id = " + employee.getId() + ": " + employee.getSurname() + " " + employee.getName() + ", зарплата: " + employee.getSalary() + "\r\n";
        }

        if (report.isEmpty()) {
            report = "Ничего не найдено\r\n";
        }

        System.out.println(header);
        System.out.println("----------------------------------");
        System.out.print(report);
        System.out.println("----------------------------------");

    }

    /**
     * Отчет по сотрудникам отдела c ЗП меньше, чем
     * Повышенная сложность
     *
     * @param salary int - критерий отбора по ЗП
     * @param department int - ID отдела
     */
    public void employeesWithSalaryLessThanReport(int salary, int department) {
        if (salary < 0 || salary == 0) {
            throw new IllegalArgumentException("Зарплата сотрудника должна быть больше нуля");
        }

        String header = "Список сотрудников отдела " + department
            + " c ЗП менее " + salary + " руб.";

        String report = "";
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null || !(department == employee.getDepartment() || department == 0)) {
                continue;
            }

            // Пропускаем элемент, если ЗП не соответствует критериям отбора
            if (employee.getSalary() >= salary) {
                continue;
            }

            report += "id = " + employee.getId() + ": " + employee.getSurname() + " " + employee.getName() + ", зарплата: " + employee.getSalary() + "\r\n";
        }

        if (report.isEmpty()) {
            report = "Ничего не найдено\r\n";
        }

        System.out.println(header);
        System.out.println("----------------------------------");
        System.out.print(report);
        System.out.println("----------------------------------");

    }

    /**
     * Отчет по сотрудникам организации в целом с ЗП меньше, чем
     * Повышенная сложность
     *
     * @param salary - критерий отбора по ЗП
     */
    public void employeesWithSalaryLessThanReport(int salary) {
//        _employeesReport(0, false, salary);
        if (salary < 0 || salary == 0) {
            throw new IllegalArgumentException("Зарплата сотрудника должна быть больше нуля");
        }

        String header = "Список сотрудников организации "
            + " c ЗП менее " + salary + " руб.";

        String report = "";
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null) {
                continue;
            }

            // Пропускаем элемент, если ЗП не соответствует критериям отбора
            if (employee.getSalary() >= salary) {
                continue;
            }

            report += "id = " + employee.getId() + ": " + employee.getSurname() + " " + employee.getName() + ", зарплата: " + employee.getSalary() + "\r\n";
        }

        if (report.isEmpty()) {
            report = "Ничего не найдено\r\n";
        }

        System.out.println(header);
        System.out.println("----------------------------------");
        System.out.print(report);
        System.out.println("----------------------------------");

    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < employees.length; i++) {
            result += i + " " + employees[i] + "\r\n";
        }
        return result;
    }
}
