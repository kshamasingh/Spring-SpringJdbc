package com.spring.jdbc.dao;

import com.spring.jdbc.Entities.Student;

import java.util.List;

public interface StudentDao {

    int insert(Student student);

    Student selectSingleRow(int studentId);

    List<Student> selectAllRow();

    int updateName(Student student, String name);

    int updateCity(Student student, String city);


}
