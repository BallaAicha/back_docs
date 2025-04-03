//package com.docmanager.controller;
//
//import com.docmanager.dto.apis.APIServiceDTO;
//import com.docmanager.dto.apis.CreateAPIServiceRequest;
//import com.docmanager.dto.apis.GetAllTrigrammesRequest;
//import com.docmanager.dto.apis.GetServicesByTrigrammeRequest;
//import com.docmanager.dto.apis.usecases.CreateAPIService;
//import com.docmanager.dto.apis.usecases.GetAllTrigrammes;
//import com.docmanager.dto.apis.usecases.GetServicesByTrigramme;
//import com.docmanager.dto.apis.usecases.RequestContext;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.beans.factory.annotation.Qualifier;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
//public class APIServiceAPI implements CreateAPIService {
//
//    @Qualifier("createAPIServiceImpl")
//    private final CreateAPIService createAPIService;
//
//    private final GetServicesByTrigramme getServicesByTrigramme;
//    @GetMapping("/trigramme/{trigramme}")
//    public List<APIServiceDTO> handle(GetServicesByTrigrammeRequest input, @ModelAttribute RequestContext context) {
//        return getServicesByTrigramme.handle(input, context);
//    }
//
//    @PostMapping
//    public APIServiceDTO handle(@RequestBody CreateAPIServiceRequest input, @ModelAttribute RequestContext context) {
//        return createAPIService.handle(input, context);
//    }
//
//    private final GetAllTrigrammes getAllTrigrammes;
//    @GetMapping("/trigrammes")
//    public List<String> handle(GetAllTrigrammesRequest input, @ModelAttribute RequestContext context) {
//        return getAllTrigrammes.handle(input, context);
//    }
//
//
//
//}

package com.docmanager.controller;

import com.docmanager.dto.apis.APIServiceDTO;
import com.docmanager.dto.apis.CreateAPIServiceRequest;
import com.docmanager.dto.apis.GenerateDocumentationRequest;
import com.docmanager.dto.apis.GetAllTrigrammesRequest;
import com.docmanager.dto.apis.GetServicesByTrigrammeRequest;
import com.docmanager.dto.apis.usecases.CreateAPIService;
import com.docmanager.dto.apis.usecases.GetAllTrigrammes;
import com.docmanager.dto.apis.usecases.GetServiceById;
import com.docmanager.dto.apis.usecases.GetServicesByTrigramme;
import com.docmanager.dto.apis.usecases.RequestContext;
import com.docmanager.service.PDFGenerationService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class APIServiceAPI {

    @Qualifier("createAPIServiceImpl")
    private final CreateAPIService createAPIService;
    private final GetServicesByTrigramme getServicesByTrigramme;
    private final GetAllTrigrammes getAllTrigrammes;
    private final GetServiceById getServiceById;
    private final PDFGenerationService pdfGenerationService;

    @GetMapping("/trigramme/{trigramme}")
    public List<APIServiceDTO> getServicesByTrigramme(@PathVariable String trigramme, @ModelAttribute RequestContext context) {
        GetServicesByTrigrammeRequest request = new GetServicesByTrigrammeRequest();
        request.setTrigramme(trigramme);
        return getServicesByTrigramme.handle(request, context);
    }

    @PostMapping
    public APIServiceDTO createService(@RequestBody CreateAPIServiceRequest input, @ModelAttribute RequestContext context) {
        return createAPIService.handle(input, context);
    }

    @GetMapping("/trigrammes")
    public List<String> getAllTrigrammes(@ModelAttribute RequestContext context) {
        GetAllTrigrammesRequest request = new GetAllTrigrammesRequest();
        return getAllTrigrammes.handle(request, context);
    }

    @GetMapping("/{id}")
    public APIServiceDTO getServiceById(@PathVariable UUID id, @ModelAttribute RequestContext context) {
        return getServiceById.handle(id, context);
    }

    @GetMapping("/{id}/documentation/pdf")
    public ResponseEntity<byte[]> generateDocumentation(@PathVariable UUID id, @ModelAttribute RequestContext context) {
        try {
            APIServiceDTO service = getServiceById.handle(id, context);
            byte[] pdfContent = pdfGenerationService.generateApiDocumentation(service);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            // Définir l'en-tête pour que le navigateur télécharge le fichier
            String filename = service.getTrigramme() + "_Documentation.pdf";
            headers.setContentDispositionFormData("attachment", filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
        } catch (DocumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}