import java.util.Arrays;

public class EmployeeBook {
    private final Employee[] employees = new Employee[10];

    private void validateDepartmentId(int department) {
        if (department < 1 || department > 5) {
            throw new IllegalArgumentException("ID отдела должен быть в диапазоне [1 - 5]");
        }
    }

    /**
     * Добавление нового сотрудника
     *
     * @param employee
     * @return
     */
    public boolean addEmpoloyee(Employee employee) {
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
     * Удалеие сотрудика с указанным ID
     *
     * @param id
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
     *
     * @param id
     * @return
     */
    public Employee findEmployeeById(int id) {
        Employee result = null;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getId() == id) {
                result = employees[i];
                break;
            }
        }
        return result;
    }

    /**
     * расчет фонда оплаты ЗП
     *
     * @param department - ID отдела (0 - по всей организации)
     * @return
     */
    private int _calculateTotalSalary(int department) {
        int total = 0;
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null || !(department == employee.getDepartment() || department == 0)) {
                continue;
            }
            total += employee.getSalary();
        }
        return total;
    }

    /**
     * поиск сотрудника с максимальной и минимальной ЗП
     *
     * @param department - ID отдела (0- по всей организации)
     * @param max        - true/false - пооиск сотрудника с максимальной/минимальной ЗП
     * @return
     */
    private Employee _findEmployeeWithMaxOrMinSalary(int department, boolean max) {
        Employee result = null;
        boolean isFirst = true;
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null || !(department == employee.getDepartment() || department == 0)) {
                continue;
            }

            // Устанавливаем значение результата, равным теущему
            if (
                    isFirst // Это первый непустой элемент, совпадающий по номеру отдела
                            || max && employee.getSalary() > result.getSalary() // поиск по максимальной зарплате, и зарплата больше, чем у установленного ранее
                            || !max && employee.getSalary() < result.getSalary() // поиск по минимальной зарплате, и зарплата меньше, чем у установленного ранее
            ) {
                result = employee;
                isFirst = false; // Следующий элемент уже не будет первым
            }

        }
        return result;
    }

    /**
     * расчет средней ЗП
     *
     * @param department - ID отдела (0 - по всей организации)
     * @return
     */
    private int _calculateAverageSalary(int department) {
        int total = 0;
        int amount = 0;
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null || !(department == employee.getDepartment() || department == 0)) {
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
     * Индексация ЗП
     * @param department  - ID отдела (0 - по всей организации)
     * @percent department  - процент повышения ЗП
     */
    private void _salaryIndexation(int department, int percent) {
        if (percent <= 0) {
            throw new IllegalArgumentException("Процент повышения зарплаты должен быть больше нуля");
        }
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null || !(department == employee.getDepartment() || department == 0)) {
                continue;
            }
            int salary = employee.getSalary();
            salary += salary * percent / 100;
            employee.setSalary(salary);
        }
    }

    /**
     * Отчет по сотрудникам общий
     * @param department - ID отдела (0 - по всей организации)
     * @param moreThan - true/false ЗП больше, чем/меньше, чем
     * @param salary - критерий по ЗП
     */
    private void _employeesReport(int department, boolean moreThan, int salary) {
        if (salary < 0 || salary == 0 && !moreThan) {
            throw new IllegalArgumentException("Зарплата сотрудника должна быть больше нуля");
        }

        String header = "Список сотрудников";
        if (department != 0) {
            header += " отдела " + department;
        } else {
            header += " организации";
        }

        if (salary > 0) {
            if (moreThan) {
                header += " c ЗП от " + salary + " руб.";
            } else {
                header += " c ЗП менее " + salary + " руб.";
            }
        }

        String report = "";
        for (Employee employee : employees) {
            // Пропускаем элемент, если он пустой, или не совпадает по номеру отделу
            if (employee == null || !(department == employee.getDepartment() || department == 0)) {
                continue;
            }

            // Пропускаем элемент, если ЗП не соответствует критериям отбора
            if (moreThan && employee.getSalary() < salary || !moreThan && employee.getSalary() >= salary) {
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
     * поиск сотрудника с максимальной ЗП по отделу
     * @param department - ID отдела
     * @return
     */
    public Employee findEmployeeWithMaxSalary(int department) {
        validateDepartmentId(department);
        return _findEmployeeWithMaxOrMinSalary(department, true);
    }

    /**
     * поиск сотрудника с максимальной ЗП по всей организации
     * @return
     */
    public Employee findEmployeeWithMaxSalary() {
        return _findEmployeeWithMaxOrMinSalary(0, true);
    }

    /**
     * поиск сотрудника с минимальной ЗП по отделу
     * @param department - ID отдела
     * @return
     */
    public Employee findEmployeeWithMinSalary(int department) {
        validateDepartmentId(department);
        return _findEmployeeWithMaxOrMinSalary(department, false);
    }

    /**
     * поиск сотрудника с минимальной ЗП по всей организации
     * @return
     */
    public Employee findEmployeeWithMinSalary() {
        return _findEmployeeWithMaxOrMinSalary(0, false);
    }

    /**
     * расчет фонда ЗП по отделу
     * @param department - ID отдела
     * @return
     */
    public int calculateTotalSalary(int department) {
        validateDepartmentId(department);
        return _calculateTotalSalary(department);
    }

    /**
     * расчет фонда ЗП по всей организации
     *  @return
     */
    public int calculateTotalSalary() {
        return _calculateTotalSalary(0);
    }

    /**
     * расчет средней ЗП по отделу
     * @param department
     * @return
     */
    public int calculateAverageSalary(int department) {
        validateDepartmentId(department);
        return _calculateAverageSalary(department);
    }

    /**
     * расчет средней ЗП по всей организации
     * @return
     */
    public int calculateAverageSalary() {
        return _calculateAverageSalary(0);
    }

    /**
     * Индексация ЗП сотрудникам отдела
     * @param percent - процент повышения ЗП
     * @param department - ID отдела
     */
    public void salaryIndexation(int percent, int department) {
        validateDepartmentId(department);
        _salaryIndexation(department, percent);
    }

    /**
     * Индексация ЗП всем сотрдуникам организации
     * @param percent - процент повышения ЗП
     */
    public void salaryIndexation(int percent) {
        _salaryIndexation(0, percent);
    }

    /**
     * Отчет по сотрудникам отдела
     * @param department - ID отдела
     */
    public void employeesReport(int department) {
        _employeesReport(department, true, 0);
    }

    /**
     * Отчет по сотрудникам организации в целом
     */
    public void employeesReport() {
        _employeesReport(0, true, 0);
    }

    /**
     * Отчет по сотрудникам отдела c ЗП больше, чем
     * @param salary - критерий отбора по ЗП
     * @param department - ID отдела
     */
    public void employeesWithSalaryMoreThanReport(int salary, int department) {
        _employeesReport(department, true, salary);
    }

    /**
     * Отчет по сотрудникам организации в целом c ЗП больше, чем
     * @param salary - критерий отбора по ЗП
     */
    public void employeesWithSalaryMoreThanReport(int salary) {
        _employeesReport(0, true, salary);
    }

    /**
     * Отчет по сотрудникам отдела c ЗП меньше, чем
     * @param salary - критерий отбора по ЗП
     * @param department - ID отдела
     */
    public void employeesWithSalaryLessThanReport(int salary, int department) {
        _employeesReport(department, false, salary);
    }

    /**
     * Отчет по сотрудникам организации в целом c ЗП меньше, чем
     * @param salary - критерий отбора по ЗП
     */
    public void employeesWithSalaryLessThanReport(int salary) {
        _employeesReport(0, false, salary);
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
