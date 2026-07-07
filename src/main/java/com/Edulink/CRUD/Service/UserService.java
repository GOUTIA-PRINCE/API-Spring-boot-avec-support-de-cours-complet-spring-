package com.Edulink.CRUD.Service;

import com.Edulink.CRUD.Entite.User;
import com.Edulink.CRUD.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

//definition de la couche metier qui va implementer les fonctionnalites
@Service
public class UserService {

    //injection des classe utiles
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder PasswordEncoder;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder PasswordEncoder) {
        this.userRepository = userRepository;
        this.PasswordEncoder = PasswordEncoder;
    }

    //Recuperation de tous les utilisateurs
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    //Recuperation d'un utilisateur
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'id : " + id));
    }

    //definition de la methode pour enregistrer un utilisateur
    public User AddUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Un utilisateur existe deja avec cet email : " + user.getEmail());
        }
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //modification d'un utilisateur
    public User UpdateUser(Long id,User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'id : " + id));

        if(userRepository.findByEmail(userDetails.getEmail()) != null && user.getEmail().equals(userDetails.getEmail())) {
            throw new RuntimeException("Un utilisateur existe deja avec cet email : " + userDetails.getEmail());
        }

        user.setName(userDetails.getName());;
        user.setEmail(userDetails.getEmail());
        user.setPassword(PasswordEncoder.encode(userDetails.getPassword()));
        return userRepository.save(user);
    }

    //suppression d'un utilisateur
    public User DeleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Utilisateur introuvable avec l'id : " + id));
             userRepository.deleteById(id);
             return  user;
    }
}
