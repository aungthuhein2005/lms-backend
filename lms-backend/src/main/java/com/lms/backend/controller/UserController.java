package com.lms.backend.controller;

import com.lms.backend.dto.UserRequest;
import com.lms.backend.entity.User;
import com.lms.backend.service.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }
    
   


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        try {
        	User createdUser = userService.createUser(userRequest);
            return ResponseEntity.ok(createdUser);
        }catch(Exception e) {
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","An unexpected error occurred.")); 
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> checkUserDependencies(@PathVariable int id) {
        boolean hasStudent = userService.hasStudentReference(id);
        boolean hasTeacher = userService.hasTeacherReference(id); 

        if (hasStudent || hasTeacher) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("message", "User is referenced as " +
                    (hasStudent ? "a student" : "") +
                    (hasStudent && hasTeacher ? " and " : "") +
                    (hasTeacher ? "a teacher" : "") +
                    ". Do you want to delete all related data?"));
        }

        userService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully."));
    }


    @DeleteMapping("/{id}/force")
    public ResponseEntity<?> forceDelete(@PathVariable int id) {
    	  boolean hasStudent = userService.hasStudentReference(id);
          boolean hasTeacher = userService.hasTeacherReference(id); 
          userService.forceDeleteUserAndReferences(id, hasStudent, hasTeacher);
        return ResponseEntity.ok(Map.of("message","User and related student data deleted successfully."));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
//        User updatedUser = userService.createUser(user);
//        return ResponseEntity.ok(updatedUser);
//    }
}
