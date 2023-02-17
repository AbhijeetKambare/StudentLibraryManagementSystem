package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.DTOS.EntryStudentDto;
import com.example.LibraryManagementSystem.DTOS.StudentUpdateRequestDto;
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
    //previously we used postman now for ddto we are convertiing dto to student object;which line include dto comment
    public String addStudent(EntryStudentDto studentDto){
        Student student=new Student();
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setCountry(studentDto.getCountry());
        student.setMobNo(studentDto.getMobNo());
        student.setEmail(studentDto.getEmail());//upper 5 lines for adding dtos info into studentobject



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
    public String updateMob(StudentUpdateRequestDto updateRequestDto){
        //if we directly updated like by saving this student but then apart from specific record of newstudent other records o
        //existing student it will null to avoid that we are getting original then modifying it
        Student originalStudent=studentRepository.findById(updateRequestDto.getId()).get();
        originalStudent.setMobNo(updateRequestDto.getMobNo());
        studentRepository.save(originalStudent);
        return "mobile no updated succesfully;";
    }
}
