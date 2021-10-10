package com.spring.jdbc.myApplication;

import com.spring.jdbc.Entities.Student;
import com.spring.jdbc.utils.JdbcConfig;
import com.spring.jdbc.dao.StudentDao;
import jdk.nashorn.internal.ir.SwitchNode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {

        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
        StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
        Student student = new Student();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Press 1 to insert student data");
        System.out.println("Press 2 to update student name");
        System.out.println("Press 3 to update student name");
        System.out.println("Press 4 to select single row");
        System.out.println("Press 5 to select multiple row");
        System.out.println("Press 6 to exit");
        int input = Integer.parseInt(bufferedReader.readLine());

        switch (input){
            case 1:
                System.out.println("Enter Student Id: " );
                int studentId = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Enter Student Name: " );
                String studentName = bufferedReader.readLine();
                System.out.println("Enter Student City: " );
                String studentCity = bufferedReader.readLine();
                student.setName(studentName);
                student.setId(studentId);
                student.setCity(studentCity);
                int insertResult = studentDao.insert(student);
                System.out.println("Result : " + insertResult);
                break;
            case 2:
                System.out.println("Enter Student Name: " );
                String sName = bufferedReader.readLine();
                int updateResult = studentDao.updateName(student, sName);
                System.out.println("Result : " + updateResult);
                break;
            case 3:
                System.out.println("Enter Student City: " );
                String sCity = bufferedReader.readLine();
                int updateCityResult = studentDao.updateName(student, sCity);
                System.out.println("Result : " + updateCityResult);
                break;
            case 4:
                System.out.println("Enter Student Id: " );
                int sId = Integer.parseInt(bufferedReader.readLine());
                Student studentObj = studentDao.selectSingleRow(sId);
                System.out.println("Id: " + studentObj.getId());
                System.out.println("Name: " + studentObj.getName());
                System.out.println("City: " + studentObj.getCity());
                break;
            case 5:
                List<Student> studentList = studentDao.selectAllRow();
                for(int i = 0; i < studentList.size(); i++) {
                    Student studentObjList = studentList.get(i);
                    System.out.println(studentObjList.getId() + ", " + studentObjList.getName() + ", " + studentObjList.getCity());
                }
                break;
            case 6:
                break;
        }
    }
}
