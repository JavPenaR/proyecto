package cl.proyecto.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.proyecto.proyecto.model.user;
import cl.proyecto.proyecto.repository.userRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class userService {

    @Autowired
    private userRepository UserRepository;

    public List<user> findAll(){
        return UserRepository.findAll();
    }

    public user saveUser(user usr){
        return UserRepository.save(usr);
    }

    public user findById(Integer id){
        return UserRepository.findById(id).orElse(null);
    }

    public user update(user usr, Integer id){
        user aux = findById(id);
        if (aux != null)  {
            aux.setNombre(usr.getNombre());
            aux.setApPaterno(usr.getApPaterno());
            aux.setApMaterno(usr.getApMaterno());
            aux.setMail(usr.getMail());
            aux.setRun(usr.getRun());
            aux.setRol(usr.getRol());
            UserRepository.save(aux);
        }
        return aux;
    }

    public void deleteUsr(Integer id){
        user aux = findById(id);
        if(aux != null){
            UserRepository.delete(aux);
        }
        return;
    }



}
