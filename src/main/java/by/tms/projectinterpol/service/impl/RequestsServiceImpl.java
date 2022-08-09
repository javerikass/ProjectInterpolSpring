package by.tms.projectinterpol.service.impl;

import by.tms.projectinterpol.dao.RequestDAO;
import by.tms.projectinterpol.dto.RequestsDTO;
import by.tms.projectinterpol.entity.Gender;
import by.tms.projectinterpol.entity.Requests;
import by.tms.projectinterpol.entity.Status;
import by.tms.projectinterpol.mapper.MapperUtil;
import by.tms.projectinterpol.service.RequestsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RequestsServiceImpl implements RequestsService {

    @Autowired
    private RequestDAO requestDAO;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public long save(RequestsDTO requestsDTO) {
        return requestDAO.save(modelMapper.map(requestsDTO, Requests.class));
    }

    @Override
    public void update(RequestsDTO requestsDTO) {
        requestDAO.update(modelMapper.map(requestsDTO, Requests.class));
    }

    @Override
    public void delete(RequestsDTO requestsDTO) {
        requestDAO.delete(modelMapper.map(requestsDTO, Requests.class));
    }

    @Override
    public List<RequestsDTO> findAll() {
        return MapperUtil.convertList(requestDAO.findAll(), requests -> (modelMapper.map(requests, RequestsDTO.class)));
    }

    @Override
    public List<RequestsDTO> findRequestsByApproval(boolean approved) {
        return MapperUtil.convertList(requestDAO.findRequestsByApproval(approved), requests -> (modelMapper.map(requests, RequestsDTO.class)));
    }

    @Override
    public List<RequestsDTO> findRequestByName(String firstName, String lastName) {
        return MapperUtil.convertList(requestDAO.findRequestByName(firstName, lastName), requests -> (modelMapper.map(requests, RequestsDTO.class)));
    }

    @Override
    public List<RequestsDTO> findRequestByAge(int age) {
        return MapperUtil.convertList(requestDAO.findRequestByAge(age), requests -> (modelMapper.map(requests, RequestsDTO.class)));
    }

    @Override
    public List<RequestsDTO> findRequestByNationality(String nationality) {
        return MapperUtil.convertList(requestDAO.findRequestByNationality(nationality), requests -> (modelMapper.map(requests, RequestsDTO.class)));
    }

    @Override
    public List<RequestsDTO> findRequestsByGender(Gender gender) {
        return MapperUtil.convertList(requestDAO.findRequestsByGender(gender), requests -> (modelMapper.map(requests, RequestsDTO.class)));
    }

    @Override
    public long findAmountRequestByStatusAndApproval(Status status, boolean approved) {
        return requestDAO.findAmountRequestByStatusAndApproval(status, approved);
    }

    @Override
    public List<RequestsDTO> findRequestsByStatusAndApprovalWithLimitAndOffset(Status status, boolean approved, String limit, String offset) {
        return MapperUtil.convertList(requestDAO.findRequestsByStatusAndApprovalWithLimitAndOffset(status, approved, limit, offset), requests -> (modelMapper.map(requests, RequestsDTO.class)));
    }
}
