package com.spring.jdbc.dao.impl;

import com.spring.jdbc.Entities.Student;
import com.spring.jdbc.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("studentDao")
public class StudentDaoImpl implements StudentDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Student student) {
        String query = "insert into student(id,name,city) values(?,?,?)";
        int result = this.jdbcTemplate.update(query,student.getId(),student.getName(),student.getCity());
        return result;
    }

    @Override
    public Student selectSingleRow(int studentId) {
        String query = "SELECT * FROM student WHERE id = ?";
        Student student = (Student) this.jdbcTemplate.queryForObject(query, new Object[]{studentId}, new BeanPropertyRowMapper(Student.class));
        return student;
    }

    @Override
    public List<Student> selectAllRow() {
        String sql = "SELECT * FROM student";
        List<Student> students = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Student.class));
        return students;
    }

    @Override
    public int updateName(Student st, String name) {
        String query = "UPDATE student SET name = ? WHERE id = " + st.getId();
        int result = this.jdbcTemplate.update(query, name);
        return result;
    }

    @Override
    public int updateCity(Student st, String city) {
        String query = "UPDATE student SET city = ? WHERE id = " + st.getId();
        int result = this.jdbcTemplate.update(query, city);
        return result;
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
