package ro.developmentfactory.students;

import java.util.*;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toSet;

public class StudentService {

    public List<Student> getFiltredStudents(List <Student> students) {
        return students.stream().filter(a -> a.getFirstName().startsWith("A")).collect(toList());
    }

    public Map<String, Set<String>> getNamesByDepertament(List<Student> students) {
        return students.stream().collect(groupingBy(Student::getDepartamentName, mapping(Student::getFirstName, toSet())));
    }

    public OptionalInt getTotalStudents(List<Student> students) {
       return OptionalInt.of((int)students.stream().count());
    }

    public OptionalInt getMaxAgeOptional(List<Student> students) {
        return students.stream().mapToInt(s -> s.getAge()).max();
    }

    public List<String> getDdepartmentNames(List<Student> students) {
        return students.stream().map(Student::getDepartamentName).distinct().toList();
    }

    public  Map<String, Long> getStudentsByDepertament(List<Student> students){
        return students.stream().collect(groupingBy(Student::getDepartamentName, counting()));
    }

    public List<Student> getStudentsUnder30(List<Student> students) {
        return  students.stream().filter(student -> student.getAge() < 30).collect(toList());
    }

    public List<Student> getRankBetween50and100(List<Student> students) {
        return students.stream().filter(student -> student.getAverageGrade() >=50 && student.getAverageGrade() <=100).collect(toList());
    }

    public Map<String, Double> getAverageAge(List<Student> students) {
        return  students.stream().collect(groupingBy(Student::getGender, averagingInt(Student::getAge)));
    }

    public Map<String, Long> getMaxStudents(List<Student> students) {
        return students.stream().collect(groupingBy(Student::getDepartamentName,counting()));
    }

    public Map<String, Double> getAverageRank(List<Student> students) {
        return students.stream().collect(groupingBy(Student::getDepartamentName, averagingInt(Student::getAverageGrade)));
    }

    public Map<String, Optional<Student>> getAverageRankMax(List<Student> students) {
        return students.stream().collect(groupingBy(Student::getDepartamentName, maxBy(Comparator.comparingInt(Student::getAverageGrade))));
    }

    public List<Student> getSortedList(List<Student> students) {
        return students.stream().sorted(Comparator.comparingInt(Student::getAverageGrade)).collect(toList());
    }

    public Optional<Student> getSecondHighestRank(List<Student> students) {
        return students.stream().sorted(Comparator.comparingInt(Student ::getAverageGrade)).skip(1).findFirst();
    }
}
