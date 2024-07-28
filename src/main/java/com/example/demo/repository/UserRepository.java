package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.model.request.UserDisplay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsernameAndPassword(String username, String password);

    @Query("SELECT u.id FROM User u WHERE u.username = :username")
    Long findIdByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :userId ")
    void resetPassword(@Param("userId") Long userId, @Param("password") String password);

    User findUserByUsername(String username);

    UserDisplay findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByNumberPhone(String numberPhone);

    boolean existsByEmail(String email);

    List<User> findAllByRolesName(String roleName);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.email = :email AND u.id != :id")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.numberPhone = :numberPhone AND u.id != :id")
    boolean existsByNumberPhoneAndIdNot(@Param("numberPhone") String numberPhone, @Param("id") Long id);


    @Query("SELECT u FROM User u JOIN u.roles r " +
            "WHERE r.name = 'USER_ROLE' AND " +
            "(LOWER(u.username) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(u.gender) = LOWER( :name) " +
            "OR LOWER(u.numberPhone) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(u.status) = LOWER( :name) " +
            "OR cast(DATE_FORMAT(u.birthOfDate,'%Y-%m-%d') as string) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "ORDER BY u.createdDate ASC, u.fullName ASC, u.birthOfDate ASC")
    Page<User> searchCustomersByUsernameOrEmail(@Param("name") String name, Pageable pageable);

    @Query("SELECT u FROM User u JOIN u.roles r " +
            "WHERE r.name = 'ADMIN_ROLE' AND " +
            "(LOWER(u.username) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(u.gender) = LOWER( :name) " +
            "OR LOWER(u.numberPhone) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(u.status) = LOWER( :name) " +
            "OR cast(DATE_FORMAT(u.birthOfDate,'%Y-%m-%d') as string) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "ORDER BY u.createdDate ASC, u.fullName ASC, u.birthOfDate ASC")
    Page<User> searchEmployeesByUsernameOrEmail(@Param("name") String name, Pageable pageable);

}
