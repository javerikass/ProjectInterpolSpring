package by.tms.projectinterpol.controller;

import by.tms.projectinterpol.dto.RequestsDTO;
import by.tms.projectinterpol.entity.Status;
import by.tms.projectinterpol.service.RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RequestController {

    @Autowired
    private RequestsService requestsService;
    @Autowired
    private Validator requestsValidator;

    @GetMapping("/missing/{offset}")
    public String missing(Model model, @PathVariable String offset) {
        model.addAttribute("missingPages",
                (int) Math.ceil(requestsService.findAmountRequestByStatusAndApproval(Status.MISSING, true) / 5.0));
        model.addAttribute("allApprovedMissingRequests",
                requestsService.findRequestsByStatusAndApprovalWithLimitAndOffset(Status.MISSING, true, "5", offset));
        return "missing";
    }

    @GetMapping("/wanted/{offset}")
    public String wanted(Model model, @PathVariable String offset) {
        model.addAttribute("wantedPages",
                (int) Math.ceil(requestsService.findAmountRequestByStatusAndApproval(Status.WANTED, true) / 5.0));
        model.addAttribute("allApprovedWantedRequests",
                requestsService.findRequestsByStatusAndApprovalWithLimitAndOffset(Status.WANTED, true, "5", offset));
        return "wanted";
    }

    @GetMapping("/requests")
    public String requestsApprove(Model model) {
        List<RequestsDTO> requestsByApproval = requestsService.findRequestsByApproval(false);
        model.addAttribute("allRequests", requestsByApproval);
        return "requests";
    }

    @GetMapping("/createRequest")
    public String requests(Model model) {
        model.addAttribute("request", new RequestsDTO());
        return "createRequest";
    }

    @PostMapping("/createRequest")
    public String pullRequests(@Valid @ModelAttribute("request") RequestsDTO requestsDTO, Errors errors) {
        requestsValidator.validate(requestsDTO, errors);
        if (errors.hasErrors()) {
            return "createRequest";
        }
        requestsDTO.setId(requestsService.save(requestsDTO));
        return "redirect:/createRequest";
    }

    @PostMapping("/approveRequest")
    public String approveRequest(RequestsDTO requestsDTO) {
        if (requestsDTO.isApproved()) {
            requestsService.update(requestsDTO);
        } else {
            requestsService.delete(requestsDTO);
        }
        return "redirect: /requests";
    }

    @PostMapping("/removeMissingRequest")
    public String removeMissingRequest(RequestsDTO requestsDTO) {
        requestsService.delete(requestsDTO);
        return "redirect:/missing/0";
    }

    @PostMapping("/removeWantedRequest")
    public String removeWantedRequest(RequestsDTO requestsDTO) {
        requestsService.delete(requestsDTO);
        return "redirect:/wanted/0";
    }


}
