package com.mx.apiPlataformasEspeciales2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mx.apiPlataformasEspeciales2.dto.Transaccion;

import java.util.Optional;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    @Query("SELECT t FROM Transaccion t WHERE t.referencia = :referencia")
    Optional<Transaccion> findByReferencia(@Param("referencia") String referencia);
}

