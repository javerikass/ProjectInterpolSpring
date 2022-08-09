package by.tms.projectinterpol.mapper;

import by.tms.projectinterpol.dto.RequestsDTO;
import by.tms.projectinterpol.entity.Requests;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public RequestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RequestsDTO convertToDTO(Requests requests) {
        return modelMapper.map(requests, RequestsDTO.class);
    }

    public Requests convertToRequestEntity(RequestsDTO requestsDTO) {
        return modelMapper.map(requestsDTO, Requests.class);
    }
}
