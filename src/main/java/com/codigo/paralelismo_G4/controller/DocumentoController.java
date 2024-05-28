package com.codigo.paralelismo_G4.controller;

import com.codigo.paralelismo_G4.service.DocumentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documentos/v1")
public class DocumentoController {

    private final DocumentoService documentoService;


    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocumento(@RequestParam("archivo")MultipartFile multipartFile){
        return documentoService.uploadDocument(multipartFile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getDocumento(@PathVariable Long id){
        return documentoService.getDocumento(id);
    }
}
