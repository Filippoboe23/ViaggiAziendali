package com.epicode.ViaggiAziendali.repository;

import com.epicode.ViaggiAziendali.entity.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio,Long> {

}