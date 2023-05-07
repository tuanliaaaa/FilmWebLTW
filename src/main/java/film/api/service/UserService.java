package film.api.service;

import film.api.DTO.UserByAdminDTO;
import film.api.models.User;
import film.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public List<User> findUsersByNameContain(String name) {
        return userRepository.findUsersByNameContain(name);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User updateUser(Long id, UserByAdminDTO userPatchDTO) {
        Object s=userRepository.findById(id);
        User user = userRepository.findById(id).orElse(null);

        if(userPatchDTO.getFullname() != null) {
            user.setFullname(userPatchDTO.getFullname());
        }

        if(userPatchDTO.getUsername() != null) {
            user.setUsername(userPatchDTO.getUsername());
        }

        return userRepository.save(user);
    }

}
