package com.example.blog.user;

import com.example.blog._core.error.ex.Exception400;
import com.example.blog._core.error.ex.Exception401;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    @Transactional
    public User findById(String username) {
        Query q = em.createQuery("select u from User u where u.username = :username",User.class);
        q.setParameter("username", username);

        try {
            return (User) q.getSingleResult();
        } catch (RuntimeException e) {
            throw new Exception401("아이디나 비밀번호가 일치하지 않습니다.");
        }


    }
}
