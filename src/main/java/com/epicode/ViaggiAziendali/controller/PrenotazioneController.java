package com.epicode.ViaggiAziendali.controller;

import com.epicode.ViaggiAziendali.entity.Dipendente;
import com.epicode.ViaggiAziendali.entity.Viaggio;
import com.epicode.ViaggiAziendali.payload.PrenotazioneDTO;
import com.epicode.ViaggiAziendali.repository.DipendenteRepository;
import com.epicode.ViaggiAziendali.repository.PrenotazioneRepository;
import com.epicode.ViaggiAziendali.repository.ViaggioRepository;
import com.epicode.ViaggiAziendali.service.PrenotazioneService;
import com.epicode.ViaggiAziendali.service.ViaggioService;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    Cloudinary cloudinary;
    @Autowired
    PrenotazioneRepository prenotazioneRepository;
    @Autowired
    PrenotazioneService prenotazioneService;
    @Autowired
    ViaggioRepository viaggioRepository;
    @Autowired
    DipendenteRepository dipendenteRepository;


    @PostMapping("/nuovaprenotazione")
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneDTO creaPrenotazione(@RequestBody @Validated PrenotazioneDTO prenotazioneDTO) {
        Dipendente dipendente = dipendenteRepository.findById(prenotazioneDTO.getDipendente().getIdDipendente()).orElseThrow(() -> new RuntimeException("ID Dipendente non trovato"));
        Viaggio viaggio = viaggioRepository.findById(prenotazioneDTO.getViaggio().getId()).orElseThrow(() -> new RuntimeException("ID Viaggio non trovato"));
        prenotazioneDTO.setViaggio(viaggio);
        prenotazioneDTO.setDipendente(dipendente);
        return prenotazioneService.createPrenotazioneDto(prenotazioneDTO);

    }


    @GetMapping("/tutteleprenotazioni")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<PrenotazioneDTO> getAllPrenotazioni(){
        return prenotazioneService.getAllPrenotazioni();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PrenotazioneDTO getPrenotazioneById(@PathVariable Long id){
        return prenotazioneService.getPrenotazioneById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletePrenotazione(@PathVariable Long id){
        return prenotazioneService.deletePrenotazione(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PrenotazioneDTO modifyPrenotazione(@RequestBody PrenotazioneDTO prenotazioneDTO, @PathVariable Long id){
        return prenotazioneService.modifyPrenotazione(prenotazioneDTO,id);
    }
}