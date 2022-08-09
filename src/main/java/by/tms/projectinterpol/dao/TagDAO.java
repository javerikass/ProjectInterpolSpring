package by.tms.projectinterpol.dao;

import by.tms.projectinterpol.entity.Tag;

import java.util.Optional;

public interface TagDAO extends BaseDAO<Long, Tag> {

    Optional<Tag> findTagByName(String tagName);
}
