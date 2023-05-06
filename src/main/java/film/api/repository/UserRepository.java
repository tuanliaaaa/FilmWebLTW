package film.api.repository;

import film.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u JOIN UserRole ur ON u.Id = ur.User.Id JOIN Role r ON ur.Role.Id = r.Id WHERE r.RoleName = 'Admin'")
//    List<User> findAllAdminUsers();
//    @Query("SELECT u.ID FROM User u JOIN UserRole ur ON u.Id = ur.User.Id JOIN Role r ON ur.Role.Id = r.Id WHERE r.RoleName = 'Admin'")
//    int findAdminUserID();
    User findByUsername(String username);

}