public class Main {
    public static void main(String[] args) {
        EmployeeBook employeeBook = new EmployeeBook();

        System.out.println("1. Инициализируем книгу сотрудников");
        System.out.println("-----------------------------------");
        employeeBook.addEmpoloyee(new Employee("АННА МИХАЙЛОВНА", "ЗАРЕЦКАЯ", 1, 78300));
        employeeBook.addEmpoloyee(new Employee("ОЛЕГ ВЛАДИМИРОВИЧ", "ВИХРОВ", 1, 93600));
        employeeBook.addEmpoloyee(new Employee("СЕРГЕЙ НИКОЛАЕВИЧ", "ПОЗДНЯКОВ", 2, 95000));
        employeeBook.addEmpoloyee(new Employee("АЛЕКСАНДР АНДРЕЕВИЧ", "КУЗНЕЦОВ", 2, 110000));
        employeeBook.addEmpoloyee(new Employee("АЛЕКСАНДР ГРИГОРЬЕВИЧ", "ПУСТЫНЦЕВ", 3, 88000));
        employeeBook.addEmpoloyee(new Employee("АНАТОЛИЙ АНТОНОВИЧ", "РЫМАЕВ", 3, 98000));
        employeeBook.addEmpoloyee(new Employee("СВЕТЛАНА ВЛАДИМИРОВНА", "ШУСТОВА", 3, 93000));
        employeeBook.addEmpoloyee(new Employee("МИХАИЛ ВЛАДИМИРОВИЧ", "МОРОЗОВ", 4, 120000));
        employeeBook.addEmpoloyee(new Employee("ГЛЕБ ЕВГЕНЬЕВИЧ", "ВОСТРИКОВ", 4, 94000));
        employeeBook.addEmpoloyee(new Employee("БОРИС ПЕТРОВИЧ", "АЛЕКСАНДРОВ", 5, 150000));
        System.out.println(employeeBook);
        System.out.println();

        System.out.println("2. Удаляем сотрудника с ID = 5");
        System.out.println("------------------------------");
        employeeBook.removeEmployeeById(5);
        System.out.println(employeeBook);
        System.out.println();


        System.out.println("3. Добавляем нового сотрудника");
        System.out.println("------------------------------");
        employeeBook.addEmpoloyee(new Employee("ВИКТОР ВАСИЛЬЕВИЧ", "АГЕЕВ", 3, 105000));
        System.out.println(employeeBook);
        System.out.println();

        System.out.println("4. Фонд ЗП на месяц по организации");
        System.out.println("----------------------------------");
        System.out.println(employeeBook.calculateTotalSalary() + " руб.");
        System.out.println();

        System.out.println("5. Фонд ЗП на месяц по отделам");
        System.out.println("------------------------------");
        {
            int total = 0;
            for (int department = 1; department <= 5; department++) {
                int salary = employeeBook.calculateTotalSalary(department) ;
                total +=salary;
                System.out.println("Отдел " + department + ": Фонд ЗП " + salary);
            }
            System.out.println("-----");
            System.out.println("Итого: " + total);
        }
        System.out.println();

        System.out.println("6. Средняя ЗП по организации");
        System.out.println("----------------------------");
        System.out.println(employeeBook.calculateAverageSalary() + " руб.");
        System.out.println();

        System.out.println("7. Средняя ЗП по отделам");
        System.out.println("------------------------");
        {
            for (int department = 1; department <= 5; department++) {
                int salary = employeeBook.calculateAverageSalary(department) ;
                System.out.println("Отдел " + department + ": Средняя ЗП " + salary);
            }
        }
        System.out.println();

        System.out.println("8. Сотрудники с максимальной и минимальной ЗП по организации");
        System.out.println("------------------------------------------------------------");
        System.out.println("Сотрудник с максимальной ЗП: " + employeeBook.findEmployeeWithMaxSalary());
        System.out.println("Сотрудник с минимальной ЗП: " + employeeBook.findEmployeeWithMinSalary());
        System.out.println();

        System.out.println("9. Сотрудники с максимальной и минимальной ЗП по отделам");
        System.out.println("--------------------------------------------------------");
        {
            for (int department = 1; department <= 5; department++) {
                System.out.println("Отдел " + department);
                System.out.println("Сотрудник с максимальной ЗП: " + employeeBook.findEmployeeWithMaxSalary(department));
                System.out.println("Сотрудник с минимальной ЗП: " + employeeBook.findEmployeeWithMinSalary(department));
            }
        }
        System.out.println();

        System.out.println("10. Повышаем ЗП всем сотрудникам организации на 15%");
        System.out.println("---------------------------------------------------");
        System.out.println("До повышения");
        employeeBook.employeesReport();
        employeeBook.salaryIndexation(15);
        System.out.println("После повышения");
        employeeBook.employeesReport();
        System.out.println();

        System.out.println("11. Получаем отчеты по сотрудникам организации с ЗП до 100000 и более");
        System.out.println("---------------------------------------------------------------------");
        employeeBook.employeesWithSalaryMoreThanReport(100000);
        employeeBook.employeesWithSalaryLessThanReport(100000);
        System.out.println();

        System.out.println("11. Получаем отчеты по сотрудникам отдела 1 с ЗП до 80000 и более");
        System.out.println("-----------------------------------------------------------------");
        employeeBook.employeesWithSalaryMoreThanReport(80000, 1);
        employeeBook.employeesWithSalaryLessThanReport(80000, 1);
        System.out.println();
    }
}