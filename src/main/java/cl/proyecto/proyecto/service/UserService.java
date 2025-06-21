package cl.proyecto.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.proyecto.proyecto.dto.User;
import cl.proyecto.proyecto.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User saveUser(User usr){
        return userRepository.save(usr);
    }

    public User findById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public User update(User usr, Integer id){
        User aux = findById(id);
        if (aux != null)  {
            aux.setNombre(usr.getNombre());
            aux.setApPaterno(usr.getApPaterno());
            aux.setApMaterno(usr.getApMaterno());
            aux.setMail(usr.getMail());
            aux.setRun(usr.getRun());
            aux.setRol(usr.getRol());
            userRepository.save(aux);
        }
        return aux;
    }

    public User findByMail(String email){
        return userRepository.findByMail(email).orElse(null);
    }

    public void deleteUsr(Integer id){
        User aux = findById(id);
        if(aux != null){
            userRepository.delete(aux);
        }
        return;
    }



}
