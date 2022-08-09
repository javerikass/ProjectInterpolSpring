package by.tms.projectinterpol.dao;

import by.tms.projectinterpol.entity.Gender;
import by.tms.projectinterpol.entity.Requests;
import by.tms.projectinterpol.entity.Status;

import java.util.List;

public interface RequestDAO extends BaseDAO<Long, Requests> {

    List<Requests> findRequestByAge(Integer age);

    List<Requests> findRequestsByGender(Gender gender);

    List<Requests> findRequestsByApproval(boolean approved);

    List<Requests> findRequestByNationality(String nationality);

    List<Requests> findRequestByName(String firstName, String lastName);

    long findAmountRequestByStatusAndApproval(Status status, boolean approved);

    List<Requests> findRequestsByStatusAndApprovalWithLimitAndOffset(Status status, boolean approved, String limit, String offset);

}
