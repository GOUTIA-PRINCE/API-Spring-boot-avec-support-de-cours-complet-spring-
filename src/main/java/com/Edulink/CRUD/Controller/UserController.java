package com.Edulink.CRUD.Controller;


import com.Edulink.CRUD.Entite.User;
import com.Edulink.CRUD.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//definition du controleur qui vas recuperer les requetes des clients et appeler le service
@Controller
@RequestMapping("/api/users")
public class UserController {

    //injection du service
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //recuperation de la liste des utilisateur
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    //recuperation d'un utilisateur a partir de son ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //ajout d'un nouveau utilisateur
    @PostMapping
    public ResponseEntity<User> AddUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.AddUser(user),HttpStatus.OK);
    }

    //modifier un utilisateur existent
    @PutMapping("{id}")
    public ResponseEntity<User> UpdateUser(@PathVariable Long id, @RequestBody User user) {

        User updateUser = userService.UpdateUser(id, user);
        if (updateUser != null) {
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //suppresion d'un utilisateur a partir de son id
    @DeleteMapping("{id}")
      public ResponseEntity<User>  DeleteUser(@PathVariable Long id) {
        User DeleteUser = userService.DeleteUser(id);
          if (DeleteUser != null) {
              return new ResponseEntity<>(DeleteUser, HttpStatus.OK);
          }
          else {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }

}
