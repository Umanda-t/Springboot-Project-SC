package com.example.projectsc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CustomerRepository CustomerRepo;
    @Autowired
    private BookRepository BookRepo;

    @RequestMapping("")
    public String viewHomePage() {
        return "index";
    }



    @RequestMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }
   /** @GetMapping("/signup_form")
    public String showUserList(Model model){
        model.addAttribute("user", new User());
        return "signup_form";
    }
    **/
   @RequestMapping("/process_register")
    public String processRegister(User user) {
        //model.addAttribute("user",  user);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }
    @RequestMapping("/users_process")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
       model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @RequestMapping(path="/add")
    public @ResponseBody
    String addNewUser () {
        User n = new User();
        n.setFirstName("Qwew");
        n.setEmail("email");
        n.setLastName("Uttgb");
        n.setPassword("yrr45");
        userRepo.save(n);
        return "Saved";
    }
    //end of User

    @RequestMapping("/booksregister")
    public String showBookRegistrationForm(Model model) {
        model.addAttribute("book", new Book());
        return "books";
    }


    @RequestMapping("/book_process")
    public String bookprocessRegister(Book book) {

        BookRepo.save(book);

        return "book_register_success";
    }
    @RequestMapping("/list_Book")
    public String listBooks(Model model) {
        List<Book> listBooks = BookRepo.findAll();
        model.addAttribute("listBooks", listBooks);

        return "bookview";
    }

    //end of book
    @RequestMapping("/stafflogin")
    public String showStafflogin() {
        return "stafflogin_form";
    }

    @RequestMapping("/Login_Service")
   public String StaffLoginService() {
       return "Success";}

   // @GetMapping("/Success")
   // public String showSuccess() {
       // return "Success";}

    @RequestMapping("/bookview/delete/{id}")
    public String deleteBook(@PathVariable("id")Long id, RedirectAttributes ra)
    {
        BookRepo.deleteById(id);
        ra.addFlashAttribute("message","The Booking ID"+id+"has been deleted");

       return "redirect:/list_Book";
    }
}
