package com.epicode.ViaggiAziendali.repository;

import com.epicode.ViaggiAziendali.entity.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente,Long> {

}