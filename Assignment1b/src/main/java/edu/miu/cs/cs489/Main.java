package edu.miu.cs.cs489;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import edu.miu.cs.cs489.entities.Employee;
import edu.miu.cs.cs489.entities.PensionPlan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "Daniel", "Agar", "2018-01-17", 105945.50));
        employees.add(new Employee(2L, "Benard", "Shaw", "2019-04-03", 197750.00));
        employees.add(new Employee(3L, "Carly", "Agar", "2014-05-16", 842000.75));
        employees.add(new Employee(4L, "Wesley", "Schneider", "2019-05-02", 74500.00));

        List<PensionPlan> pensionPlans = new ArrayList<>();
        pensionPlans.add(new PensionPlan("EX1089", "2023-01-17", 100.00, employees.get(0)));
        pensionPlans.add(new PensionPlan("SM2307", "2019-11-04", 1555.50, employees.get(2)));

        printEmployeesJSON(employees, pensionPlans);
        System.out.println("---------------------------");
        printUpcomingEnrolleesJSON(employees, pensionPlans);
    }

    // Method to print list of all employees in JSON format
    private static void printEmployeesJSON(List<Employee> employees, List<PensionPlan> pensionPlans) {
        employees.sort(Comparator.comparing(Employee::getLastName)); // Sort by last name
        employees.sort(Comparator.comparing(Employee::getYearlySalary).reversed()); // Sort by yearly salary desc.

        System.out.println("List of Employees with Pension Plan Data:");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,
                        (JsonSerializer<LocalDate>) (localDate, type, jsonSerializationContext) -> new JsonPrimitive(
                                localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                                .setPrettyPrinting()
                                .create();

        for (Employee employee : employees) {
            // Check if employee has a pension plan
            PensionPlan pensionPlan = pensionPlans.stream()
                    .filter(plan -> plan.getEmployee().equals(employee))
                    .findFirst()
                    .orElse(null);

            String json = gson.toJson(employee);
            System.out.println(json);

            if (pensionPlan != null) {
                String pensionPlanJson = gson.toJson(pensionPlan);
                System.out.println("Pension Plan Data: " + pensionPlanJson);
            } else {
                System.out.println("Employee has no Pension Plan.");
            }
        }
    }

    private static void printUpcomingEnrolleesJSON(List<Employee> employees, List<PensionPlan> pensionPlans) {
        LocalDate currentDate = LocalDate.now();
        LocalDate nextMonthFirstDay = currentDate.plusMonths(1).withDayOfMonth(1);
        LocalDate nextMonthLastDay = nextMonthFirstDay.plusMonths(1).minusDays(1);

        List<Employee> upcomingEnrollees = new ArrayList<>();
        for (Employee employee : employees) {
            LocalDate employmentDate = employee.getEmploymentDate();
            long yearsOfEmployment = ChronoUnit.YEARS.between(employmentDate, currentDate);

            // upcoming enrollement verification
            boolean isQualified = yearsOfEmployment >= 5 &&
                    !isEnrolledInPensionPlan(employee, pensionPlans) &&
                    (employmentDate.isBefore(nextMonthLastDay.plusDays(1)) &&
                            employmentDate.isAfter(nextMonthFirstDay.minusDays(1)))
                    ||
                    (yearsOfEmployment == 4 &&
                            employmentDate.isBefore(nextMonthLastDay.plusDays(1)) &&
                            employmentDate.getMonth() == nextMonthFirstDay.getMonth());

            if (isQualified) {
                upcomingEnrollees.add(employee);
            }
        }

        System.out.println("Monthly Upcoming Enrollees Report:");
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,
                        (JsonSerializer<LocalDate>) (localDate, type, jsonSerializationContext) -> new JsonPrimitive(
                                localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                .setPrettyPrinting()
                .create();

        for (Employee employee : upcomingEnrollees) {
            String json = gson.toJson(employee);
            System.out.println(json);
        }
    }

    private static boolean isEnrolledInPensionPlan(Employee employee, List<PensionPlan> pensionPlans) {
        return pensionPlans.stream()
                .anyMatch(plan -> plan.getEmployee().equals(employee));
    }
}
