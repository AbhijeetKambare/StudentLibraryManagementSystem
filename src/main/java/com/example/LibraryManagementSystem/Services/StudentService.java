package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Enums.CardStatus;
import com.example.LibraryManagementSystem.Models.Card;
import com.example.LibraryManagementSystem.Models.Student;
import com.example.LibraryManagementSystem.Reposetories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public String addStudent(Student student){
        //we passed student attruibutes frofm postman
        //remaining we wil do here, from students class
        Card card=new Card();//student wiol create carrd automaticaly
        card.setCardStatus(CardStatus.ACTIVATED);//cardstatus is setting
        card.setStudent(student);//we are seeting forign key attribute in card class
        //fillling values of attributes in class
        student.setCard(card);

        //if we used unidirectional then we need to save for card also but we are using bidirectional so we dont need save for card also
        studentRepository.save(student);

        return "Student and card added succesfully";
    }
    public String getByemail(String email){
        Student student= studentRepository.findByEmail(email);
        return student.getName();
    }
    public List<String> getBycountry(String country){
        List<String> newList=new ArrayList<>();
        List<Student> studentList= studentRepository.findByCountry(country);
        for(Student student:studentList){
            newList.add(student.getName());
        }
        return newList;
    }
    public String updateMob(Student newStudent){
        //if we directly updated like by saving this student but then apart from specific record of newstudent other records o
        //existing student it will null to avoid that we are getting original then modifying it
        Student originalStudent=studentRepository.findById(newStudent.getId()).get();
        originalStudent.setMobNo(newStudent.getMobNo());
        studentRepository.save(originalStudent);
        return "mobile no updated succesfully;";
    }
}
