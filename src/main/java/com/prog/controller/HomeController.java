package com.prog.controller;

import com.prog.entity.User;
import com.prog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    // Constructor injection for better clarity and testability
    @Autowired
    public HomeController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping({"/", "/home"})
    public String home() {
        return "index";
    }

    @GetMapping("/userprofile")
    public String userProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "userprofile";
    }
    
    @GetMapping("/mindfit")
    public String mindfit(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "mindfit";
    }
    @GetMapping("/userprofile/edit")
    public String editUserProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "editProfile"; // Return the name of your edit profile template
    }

    // New POST method to handle the update profile form submission
    @PostMapping("/userprofile/edit")
    public String updateUserProfile(@ModelAttribute User updatedUser, Model model) {
        // Update the user details using the UserService
        userService.updateUser(updatedUser);
        return "redirect:/userprofile"; // Redirect back to the profile page after updating
    }
    @GetMapping("/mental-health")
    public String ment_quiz() {
        // Redirect to ment_quiz.html
        return "ment_quiz";
    }
    @GetMapping("/mental-option")
    public String m_option() {
        // Redirect to ment_quiz.html
        return "m_option";
    }
    @GetMapping("/physical-health")
    public String phy_quiz() {
        // Redirect to ment_quiz.html
        return "phy_quiz";
    }
    @GetMapping("/physical-option")
    public String p_option() {
        // Redirect to ment_quiz.html
        return "p_option";
    }
    @GetMapping("/workout")
    public String workout() {
        // Redirect to ment_quiz.html
        return "workout";
    }
    @GetMapping("/upperbody")
    public String upperbody() {
        // Redirect to ment_quiz.html
        return "upperbody";
    }
    @GetMapping("/lowerbody")
    public String lowerbody() {
        // Redirect to ment_quiz.html
        return "lowerbody";
    }
    @GetMapping("/dietplan")
    public String dietplan() {
        // Redirect to ment_quiz.html
        return "dietplan";
    }
    @GetMapping("/veg")
    public String veg() {
        // Redirect to ment_quiz.html
        return "veg";
    }
    @GetMapping("/nonveg")
    public String nonveg() {
        // Redirect to ment_quiz.html
        return "nonveg";
    }
    @GetMapping("/veg-recipe")
    public String veg_recipe() {
        // Redirect to ment_quiz.html
        return "veg_recipe";
    }
    @GetMapping("/nonveg-recipe")
    public String nonveg_recipe() {
        // Redirect to ment_quiz.html
        return "nonveg_recipe";
    }
    @GetMapping("/meditation")
    public String meditation() {
        // Redirect to ment_quiz.html
        return "meditation";
    }
    @GetMapping("/sleep")
    public String sleep() {
        // Redirect to ment_quiz.html
        return "sleep";
    }
    @GetMapping("/music")
    public String music() {
        // Redirect to ment_quiz.html
        return "music";
    }
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String register(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam int age, @RequestParam String bio,@RequestParam String gender,@RequestParam double height,@RequestParam double weight, Model model) {
        if (userService.usernameExists(username)) {
            model.addAttribute("error", "Username already exists.");
            return "signup";
        }
        if (userService.emailExists(email)) {
            model.addAttribute("error", "Email already exists.");
            return "signup";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setAge(age);
        user.setBio(bio);
        user.setGender(gender);
        user.setWeight(weight);
        user.setHeight(height);
        userService.saveUser(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/userprofile";
    }
    
    
//--------------------------------------------------------------------------
   @GetMapping("/admin")
    public String adminDashboard1(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin_dashboard"; // Admin dashboard view
    }

    @PostMapping("/admin/edit")
    public String editUser(@ModelAttribute User updatedUser) {
        userService.updateUser(updatedUser);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
    
    
    

//-------------------------------------------------------------------------
    
    }    
