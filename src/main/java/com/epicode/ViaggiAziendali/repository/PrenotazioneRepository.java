package com.epicode.ViaggiAziendali.repository;

import com.epicode.ViaggiAziendali.entity.Dipendente;
import com.epicode.ViaggiAziendali.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Long> {
    @Query("SELECT COUNT(p) > 0 FROM prenotazioni p WHERE p.dipendente = :dipendente AND p.viaggio.dataViaggio = :dataViaggio")
    boolean existsPrenotazioneByDipendenteAndData(@Param("dipendente") Dipendente dipendente, @Param("dataViaggio") LocalDate dataViaggio);
}