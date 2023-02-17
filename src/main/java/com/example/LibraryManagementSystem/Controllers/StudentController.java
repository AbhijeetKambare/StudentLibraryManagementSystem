package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.DTOS.EntryStudentDto;
import com.example.LibraryManagementSystem.DTOS.StudentUpdateRequestDto;
import com.example.LibraryManagementSystem.Models.Student;
import com.example.LibraryManagementSystem.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/addStudent")
    public String addStudent(@RequestBody()EntryStudentDto studentDto){
        return studentService.addStudent(studentDto);
    }
    @GetMapping("/get")
    public String getByEmail(@RequestParam("email") String email){
        return studentService.getByemail(email);
    }
    @GetMapping("/getByCountry")
    public List<String> getByCountry(@RequestParam("country") String country){
        return studentService.getBycountry(country);
    }
    @PutMapping("/updateMob")
    public String updateMobNo(@RequestBody() StudentUpdateRequestDto updateRequestDto){
        return studentService.updateMob(updateRequestDto);
    }
}
