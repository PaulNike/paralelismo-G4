package com.codigo.paralelismo_G4.dao;

import com.codigo.paralelismo_G4.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento,Long> {
}
