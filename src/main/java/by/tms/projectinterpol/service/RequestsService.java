package by.tms.projectinterpol.service;

import by.tms.projectinterpol.dto.RequestsDTO;
import by.tms.projectinterpol.entity.Gender;
import by.tms.projectinterpol.entity.Status;

import java.util.List;

public interface RequestsService {

    long save(RequestsDTO requestsDTO);

    void update(RequestsDTO requestsDTO);

    void delete(RequestsDTO requestsDTO);

    List<RequestsDTO> findAll();

    List<RequestsDTO> findRequestByAge(int age);

    List<RequestsDTO> findRequestsByGender(Gender gender);

    List<RequestsDTO> findRequestsByApproval(boolean approved);

    List<RequestsDTO> findRequestByNationality(String nationality);

    List<RequestsDTO> findRequestByName(String firstName, String lastName);

    long findAmountRequestByStatusAndApproval(Status status, boolean approved);

    List<RequestsDTO> findRequestsByStatusAndApprovalWithLimitAndOffset(Status status, boolean approved, String limit, String offset);
}
