package com.example.LibraryManagementSystem.Controllers;

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
    public String addStudent(@RequestBody() Student student){
        return studentService.addStudent(student);
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
    public String updateMobNo(@RequestBody() Student student){
        return studentService.updateMob(student);
    }
}
