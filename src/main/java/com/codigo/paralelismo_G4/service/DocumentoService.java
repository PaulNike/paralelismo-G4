package com.codigo.paralelismo_G4.service;

import com.codigo.paralelismo_G4.dao.DocumentoRepository;
import com.codigo.paralelismo_G4.entity.Documento;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;

    public DocumentoService(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    public ResponseEntity<String> uploadDocument(MultipartFile multipartFile){
        try{
            byte[] bytes = multipartFile.getBytes();
            String base64 = Base64.getEncoder().encodeToString(bytes);
            Documento documento = new Documento();
            documento.setContenido(base64);
            documentoRepository.save(documento);
            /*Agregando datos a la Cabecera de la solicitud*/
            HttpHeaders  headers = new HttpHeaders();
            headers.add("DATO-HEADER","ValorPrueba");

            return new ResponseEntity<>("Documento guardado con Exito con ID: "+ documento.getId(),headers, HttpStatus.OK);

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error guardando el documento " + e.getMessage());
        }
    }

    public ResponseEntity<String> getDocumento(Long id){
        Optional<Documento> documento = documentoRepository.findById(id);
        return documento.map(doc -> ResponseEntity.ok(doc.getContenido())).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
