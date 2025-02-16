package com.epicode.ViaggiAziendali.service;
import com.epicode.ViaggiAziendali.entity.Dipendente;
import com.epicode.ViaggiAziendali.mapper.DipendenteMapperDTO;
import com.epicode.ViaggiAziendali.payload.DipendenteDTO;
import com.epicode.ViaggiAziendali.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.Azienda.viaggiAziendali.mapper.DipendenteMapperDTO.toDipendenteDTO;
import static com.Azienda.viaggiAziendali.mapper.DipendenteMapperDTO.toDipendenteEntity;

@Service
public class DipendenteService {

    @Autowired
    DipendenteRepository dipendenteRepository;
    @Autowired
    DipendenteMapperDTO dipendenteMapperDTO;


    public DipendenteDTO createDipendenteDto(DipendenteDTO dipendenteDTO){
        Dipendente dipendente = toDipendenteEntity(dipendenteDTO);
        dipendenteRepository.save(dipendente);
        return dipendenteDTO;

    }
    public Long idDipendente(DipendenteDTO dipendenteDTO){
        Dipendente dipendente = toDipendenteEntity(dipendenteDTO);
        return dipendenteRepository.save(dipendente).getIdDipendente();
    }

    public DipendenteDTO modifyDipendente(DipendenteDTO dipendenteDTO, Long id){
        Dipendente dipendente = dipendenteRepository.findById(id).orElseThrow(()-> new RuntimeException("Dipendente non trovato"));
        if(dipendenteDTO.getNome() != null && !dipendenteDTO.getNome().equals(dipendente.getNome())){
            dipendente.setNome(dipendenteDTO.getNome());
        }
        if (dipendenteDTO.getCognome() != null && !dipendenteDTO.getCognome().equals(dipendente.getCognome())){
            dipendente.setCognome(dipendenteDTO.getCognome());
        }
        if(dipendenteDTO.getUsername() != null && !dipendenteDTO.getUsername().equals(dipendente.getUsername())){
            dipendente.setUsername(dipendenteDTO.getUsername());
        }
        if(dipendenteDTO.getEmail() != null && !dipendenteDTO.getEmail().equals(dipendente.getEmail())){
            dipendente.setEmail(dipendenteDTO.getEmail());
        }
        if(!dipendenteDTO.getImmagineProfilo().equals(dipendente.getImmagineProfilo())){
            dipendente.setImmagineProfilo(dipendenteDTO.getImmagineProfilo());
        }

        dipendente = dipendenteRepository.save(dipendente);
        return toDipendenteDTO(dipendente);
    }


    public List<DipendenteDTO> getAllDipendenti(){
        List<Dipendente> dipendenti = dipendenteRepository.findAll();
        List<DipendenteDTO> dipendentiDto = new ArrayList<DipendenteDTO>();
        for (Dipendente singoloDipendente : dipendenti){
            dipendentiDto.add(toDipendenteDTO(singoloDipendente));

        }
        return dipendentiDto;
    }

    public DipendenteDTO getDipendenteById(Long id){
        Optional<Dipendente> dipendente = dipendenteRepository.findById(id);
        if (dipendente.isPresent()){
            return toDipendenteDTO(dipendente.get());
        } else {
            throw new RuntimeException("Non c'è nessun Dipendente con l'ID : " + id);
        }
    }

    public String deleteDipendente(Long id){
        Optional<Dipendente> dipendenteDaEliminare = dipendenteRepository.findById(id);
        if (dipendenteDaEliminare.isPresent()){
            dipendenteRepository.delete(dipendenteDaEliminare.get());
            return "Dipendente con ID " + id + " è stato eliminato con successo!☑️";
        } else {

            throw new RuntimeException("Il dipendente con ID " + id + " non è presente nel nostro db.😒");
        }
    }

}