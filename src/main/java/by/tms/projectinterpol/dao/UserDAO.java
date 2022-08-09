package by.tms.projectinterpol.dao;

import by.tms.projectinterpol.entity.User;

import java.util.Optional;

public interface UserDAO extends BaseDAO<Long, User> {

    Optional<User> findUserByUsername(String username);
}
