package web.controllers;


import domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @ModelAttribute("students")
    @RequestMapping("/students")
    public ModelAndView students() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Student> list = studentService.listAll();
        modelAndView.addObject("students",list);
        modelAndView.setViewName("admin/students");
        return modelAndView;
    }

    @ModelAttribute("student")
    @RequestMapping(params = "new")
    public ModelAndView newStudentForm() {
        ModelAndView modelAndView = new ModelAndView();
        Student student = new Student();
        student.setName("stu");
        modelAndView.addObject(student);
        modelAndView.setViewName("admin/student");
        return modelAndView;
    }


    @RequestMapping(params = "delete")
    public String deleteStudents(@RequestParam("id") Long... ids) throws Exception {
        studentService.deleteStudents(ids);
        return "redirect:/admin/students";
    }

    //限制http请求为post
    @RequestMapping(params = "save",method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("student") Student student) throws Exception {
        studentService.addStudent(student);
        return "redirect:/admin/students";
    }

    @RequestMapping("/{id}")
    public String updateStudentForm(@PathVariable("id") long id,Model model) throws Exception{

        model.addAttribute("student", studentService.findStudent(id));
        return "admin/updateStudent";
    }
    @RequestMapping(params = "update")
    public String updateStudentInfo(@ModelAttribute("student") Student student
                                    /*, @RequestParam("student.id") long id,
                                    @RequestParam("numbers.id") long numberId,
                                    @RequestParam("classes.id") long classesId*/) throws Exception{
        /*student.setId(id);
        student.getNumbers().setId(numberId);
        student.getClasses().setId(classesId);*/
        studentService.updateStudent(student);
        return "redirect:/admin/students";
    }

}
