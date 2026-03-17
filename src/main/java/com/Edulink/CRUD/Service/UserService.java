package com.Edulink.CRUD.Service;

import com.Edulink.CRUD.Entite.User;
import com.Edulink.CRUD.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

//definition de la couche metier qui va implementer les fonctionnalites
@Service
public class UserService {

    //injection du repository
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Recuperation de tous les utilisateurs
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    //Recuperation d'un utilisateur
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    //definition de la methode pour enregistrer un utilisateur
    public User AddUser(User user) {
        return userRepository.save(user);
    }

    //modification d'un utilisateur
    public User UpdateUser(Long id,User userDetails) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            //ajout des details de l'utilisateur
            user.get().setName(userDetails.getName());
            user.get().setEmail(userDetails.getEmail());
            user.get().setPassword(userDetails.getPassword());
            return userRepository.save(user.get());
        }
        return null;
    }

    //suppression d'un utilisateur
    public User DeleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
             userRepository.deleteById(id);
             return  user.get();
        }
            return null;
    }
}
