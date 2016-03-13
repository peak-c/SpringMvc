package web.controllers;

import domain.Student;
import exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.StudentService;
import util.ImageUtil;
import web.controllers.validator.StudentValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ModelAttribute("student")
    @RequestMapping("/students")
    public Student student(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        ModelAndView modelAndView = new ModelAndView();
        Student student = (Student) request.getSession().getAttribute("student");

        System.out.println(student);
        if(student==null){
            response.sendRedirect(request.getContextPath()+"/login");
//            modelAndView.setViewName("login");
        }
//        else {
//            modelAndView.addObject("student", student);
//            modelAndView.setViewName("student/students");
//        }
        return student;
    }

    @ModelAttribute("student")
    @RequestMapping("/registForm")
    public ModelAndView newStudentForm() {
        ModelAndView modelAndView = new ModelAndView();
        Student student = new Student();
        student.setName("学生1");
        modelAndView.addObject(student);
        modelAndView.setViewName("student/student");
        return modelAndView;
    }


	//限制http请求为post
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public String addStudent(@Validated @ModelAttribute("student") Student student,
                            BindingResult result,Model model,
                             MultipartFile pictureFile,
                             HttpServletRequest request) throws CustomException {
        new StudentValidator().validate(student, result);
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError objectError:errors){
                System.out.println(objectError.getCode());
                System.out.println(objectError.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            return "/student/student";
        }

        try {
            String path = request.getServletContext().getRealPath("/upload");
            System.out.println("path:"+path);

            String filePath = ImageUtil.saveImage(path,pictureFile);
            student.setImage("/upload"+filePath);
            System.out.println("imageNames:"+filePath);
            studentService.addStudent(student);
        } catch (Exception e) {
            throw  new CustomException("该用户ID已存在"+e);
        }
        return "redirect:/login";
    }

    @RequestMapping("/{id}")
    public String updateStudentForm(@PathVariable("id") long id,Model model) throws Exception {

        model.addAttribute("student", studentService.findStudent(id));
        return "student/updateStudent";
    }
    @RequestMapping(params = "update")
    public String updateStudentInfo(@ModelAttribute("student") Student student,BindingResult result,
                                    Model model,
                                    HttpSession session ,
                                    MultipartFile pictureFile,
                                    HttpServletRequest request) throws Exception {
        new StudentValidator().validate(student, result);
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            //model.addAttribute("errors", errors);
            return "/student/updateStudent";
        }
        try {
            String path = request.getServletContext().getRealPath("/upload");

            String filePath = ImageUtil.saveImage(path,pictureFile);
            //如果新上传了图片
            if(!filePath.equals("")){
                student.setImage("/upload"+filePath);
            }
            studentService.updateStudent(student);
            Student student_ = (Student) session.getAttribute("student");
            student_ = studentService.findStudent(student_.getId());
            session.setAttribute("student",student_);
        } catch (Exception e) {
            throw  new CustomException(e.getMessage());
        }

        /**
         * 更新后应转发，如若重定向则丢失request，在students页面中。。。
         */
        return "/student/students";
    }


}
