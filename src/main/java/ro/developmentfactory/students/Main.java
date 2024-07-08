package ro.developmentfactory.students;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List <Student> students = new ArrayList<>();
        students.add(new Student("John", "Doe", 30, "Male", "Mechanical Engineering", 122));
        students.add(new Student("Jane", "Smith", 22, "Female", "Computer Engineering", 324));
        students.add(new Student ("Ann", "Gurnmeister", 56, "Female", "Biotech Engineering", 64));
        students.add(new Student("Elon", "Gated", 27, "Male", "Mechanical Engineering", 90));
        students.add(new Student("Justin", "Case", 26, "Male", "Computer Engineering", 340));
        students.add(new Student("Dianne", "Ameter", 31, "Female", "Biotech Engineering", 128));
        students.add(new Student("Joss", "Sticks", 23, "Male", "Computer Engineering", 20));

        StudentService studentService = new StudentService();

        List<Student> filteredStudents = studentService.getFiltredStudents(students);
        System.out.println("Filtered Students: " + filteredStudents);

        Map<String, Set<String>> namesByDepertament = studentService.getNamesByDepertament(students);
        System.out.println("Names by Depertament : " + namesByDepertament);

        OptionalInt totalStudents = studentService.getTotalStudents(students);
        System.out.println(totalStudents);

        OptionalInt maxAgeOptional = studentService.getMaxAgeOptional(students);
        if (maxAgeOptional.isPresent()) {
            System.out.println("Max age : " + maxAgeOptional.getAsInt());
        } else {
            System.out.println("No maximum age found.");
        }

        List<String> departmentNames = studentService.getDdepartmentNames(students);
        System.out.println("Department Names : " + departmentNames);

        Map<String, Long> studentsByDepertament = studentService.getStudentsByDepertament(students);
        System.out.println("Students by Depertament : " + studentsByDepertament);

        List<Student> studentsUnder30 = studentService.getStudentsUnder30(students);
        System.out.println("Students under 30 : " + studentsUnder30);

        List<Student> rankBetween50and100 = studentService.getRankBetween50and100(students);
        System.out.println("Rank between 50 and 100 : " + rankBetween50and100);

        Map<String, Double> averageAge = studentService.getAverageAge(students);
        System.out.println("Average age : " + averageAge);

        Map<String, Long> maxStudents = studentService.getMaxStudents(students);
        System.out.println("Max students : " + maxStudents);

        Map<String, Double> averageRank = studentService.getAverageRank(students);
        System.out.println("Average rank : " + averageRank);

        Map<String, Optional<Student>> averageRankMax = studentService.getAverageRankMax(students);
        System.out.println("Average rank max : " + averageRankMax);

        List<Student> sortedList = studentService.getSortedList(students);
        System.out.println("Sorted list : " + sortedList);

        Optional<Student> secondHighestRank = studentService.getSecondHighestRank(students);
        System.out.println("Second highest rank : " + secondHighestRank);
            }

        }
