package com.example.school.services;

import com.example.school.dto.Class;
import com.example.school.dto.Mark;
import com.example.school.dto.Person;
import com.example.school.dto.SubjectAndPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolService {

    @Autowired
    public DataSource dataSource;

    public  List<Person> selectAllPerson() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        ArrayList<Person> personList = new ArrayList<Person>();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM Person ORDER BY occupation, name;");
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                Person timePerson = new Person();
                timePerson.personId = resultSet.getInt(1);
                timePerson.name = resultSet.getString(2);
                timePerson.occupation = resultSet.getString(3);

                personList.add(timePerson);
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException exception)
            {exception.printStackTrace();}
        }


        return personList;
    }

    public List<Class> selectAllClass() {
        ArrayList<Class> classArrayList = new ArrayList<Class>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT class_id, class_name, class_year, person.name FROM Class JOIN Person ON (person.person_id = class.teacher_id)");
            resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                Class timeClass = new Class();
                timeClass.classId = resultSet.getInt(1);
                timeClass.className = resultSet.getString(2);
                timeClass.classYear = resultSet.getInt(3);
                timeClass.classTeacher = resultSet.getString(4);

                classArrayList.add(timeClass);
            }

        } catch (SQLException ex) {}
        finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();

            }catch (SQLException exception)
            {exception.printStackTrace();}
        }

        return classArrayList;
    }

    public ArrayList<Person> selectClassInfo(int year, String className) {
        ArrayList<Person> personArrayList = new ArrayList<Person>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT person.person_id, person.name, person.occupation FROM class_student " +
                    "JOIN Person ON (class_student.student_id = person.person_id) WHERE class_student.class_id = " +
                    "(SELECT class_id FROM Class WHERE class_year = " + year + " and class_name = '"+className+"');");
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                Person timePerson = new Person();
                timePerson.personId = resultSet.getInt(1);
                timePerson.name = resultSet.getString(2);
                timePerson.occupation = resultSet.getString(3);

                personArrayList.add(timePerson);
            }


        } catch (SQLException ex){} finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException exception) {exception.printStackTrace();}
        }

        return personArrayList;
    }

    public List<SubjectAndPerson> selectPersonByMark (int mark) {
        ArrayList<SubjectAndPerson> subjectAndPersonArrayList = new ArrayList<SubjectAndPerson>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT subject, person.name FROM marks JOIN person ON person.person_id = marks.student_id WHERE mark = " + mark +"; ");
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                SubjectAndPerson timeSubjectAndPerson = new SubjectAndPerson();
                timeSubjectAndPerson.subject = resultSet.getString(1);
                timeSubjectAndPerson.name = resultSet.getString(2);


                subjectAndPersonArrayList.add(timeSubjectAndPerson);
            }

        } catch (SQLException ex) {}
        finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException exception) {exception.printStackTrace();}
        }

        return subjectAndPersonArrayList;
    }

    public void insertMark(Mark mark) {

        Connection connection = null;
        PreparedStatement statement = null;

        try{

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO marks VALUES ('"+mark.subject+"', "+mark.mark+", " +
                    "(SELECT person_id FROM person WHERE name = '"+mark.studentName+"'), " +
                    "(SELECT person_id FROM person WHERE name = '"+mark.teacherName+"'));");
            statement.execute();


        }catch (SQLException ex){ex.printStackTrace();}
        finally {
            try{
                connection.close();
                statement.close();
            }catch (SQLException exception){exception.printStackTrace();}
        }

    }
}
