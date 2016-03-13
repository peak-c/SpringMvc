package web.controllers;


import domain.Numbers;
import domain.Student;
import exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private StudentService studentService;


    @RequestMapping("/login")
    public String doLogin(HttpServletRequest request,String userType, String username, String password ) throws Exception {

        System.out.println(userType + ":" + username + "------" + password);

        HttpSession session = request.getSession();
        if(userType!=null && userType.equals("admin")){
            if(username!=null && username.equals("admin")){
                session.setAttribute("admin", username);
                return "redirect:/admin/students";
            }
        }
        if(userType!=null && userType.equals("student")) {
            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {

                System.out.println("++++++++++++");
                Student student = new Student();
                Numbers numbers = new Numbers();
                numbers.setNum(username);
                student.setNumbers(numbers);
                student.setPassword(password);
                Student student_session = null;
                try {
                    student_session = studentService.login(student);
                } catch (Exception e) {
                    throw new CustomException("登录失败");
                }
                if (student_session != null) {
                    session.setAttribute("student", student_session);
                    /**
                     * 此处：因为使用的是student和admin两种作为登录用户
                     * 所以请求中未能封装成model，导致request域中无student
                     * 故可以在放入request域中
                     */

                    request.setAttribute("student", student_session);
                    return "student/students";
                }

            }
        }
        return "/login";

    }


}
