package com.epicode.ViaggiAziendali.service;

import com.epicode.ViaggiAziendali.entity.Viaggio;
import com.epicode.ViaggiAziendali.mapper.ViaggioMapperDTO;
import com.epicode.ViaggiAziendali.payload.ViaggioDTO;
import com.epicode.ViaggiAziendali.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.Azienda.viaggiAziendali.mapper.ViaggioMapperDTO.toViaggioDTO;

@Service
public class ViaggioService {


    @Autowired
    ViaggioRepository viaggioRepository;
    @Autowired
    ViaggioMapperDTO viaggioMapperDTO;


    public ViaggioDTO createViaggioDto(ViaggioDTO viaggioDTO){
        Viaggio viaggio = viaggioMapperDTO.toViaggioEntity(viaggioDTO);
        viaggioRepository.save(viaggio);
        return viaggioDTO;
    }


    public List<ViaggioDTO> getAllViaggi(){
        List<Viaggio> viaggioList = viaggioRepository.findAll();
        List<ViaggioDTO> viaggioDTOList = new ArrayList<ViaggioDTO>();
        for (Viaggio viaggio : viaggioList){
            viaggioDTOList.add(toViaggioDTO(viaggio));
        }
        return viaggioDTOList;
    }


    public ViaggioDTO getViaggioById(Long id){
        Optional<Viaggio> viaggioCercato = viaggioRepository.findById(id);
        if (viaggioCercato.isPresent()){
            return toViaggioDTO(viaggioCercato.get());
        } else {
            throw new RuntimeException("Non esiste nessun viaggio con l'ID : "+ id);
        }
    }

    public String deleteViaggio(Long id){
        Optional<Viaggio> viaggioCercato = viaggioRepository.findById(id);
        if (viaggioCercato.isPresent()){
            viaggioRepository.delete(viaggioCercato.get());
            return "Viaggio con ID " + id + " è stato eliminato con successo!";
        } else {
            throw new RuntimeException("Viaggio con ID : "+ id + " non presente nel nostro db");
        }
    }


    public ViaggioDTO modifyViaggio(ViaggioDTO viaggioDTO, Long id){
        Viaggio viaggio = viaggioRepository.findById(id).orElseThrow(() -> new RuntimeException("Viaggio non trovato nel db!😒"));
        if ( viaggioDTO.getDataViaggio() != null && !viaggioDTO.getDataViaggio().isEqual(viaggio.getDataViaggio())){
            viaggio.setDataViaggio(viaggioDTO.getDataViaggio());
        }
        if (viaggioDTO.getDestinazione() != null && !viaggioDTO.getDestinazione().equals(viaggio.getDestinazione().toLowerCase())) {
            viaggio.setDestinazione(viaggioDTO.getDestinazione());
        }
        if (viaggioDTO.getStato() != null &&  !viaggioDTO.getStato().equals(viaggio.getStato().toLowerCase())){
            viaggio.setStato(viaggioDTO.getStato());
        }
        viaggio = viaggioRepository.save(viaggio);
        return toViaggioDTO(viaggio);
    }
}