package com.epicode.ViaggiAziendali.controller;

import com.epicode.ViaggiAziendali.entity.Dipendente;
import com.epicode.ViaggiAziendali.payload.DipendenteDTO;
import com.epicode.ViaggiAziendali.payload.PrenotazioneDTO;
import com.epicode.ViaggiAziendali.service.DipendenteService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    Cloudinary cloudinary;
    @Autowired
    DipendenteService dipendenteService;



    @PostMapping("/nuovodipendente")
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteDTO creaDipendente(@RequestPart("immagineProfilo") MultipartFile immagineProfilo, @RequestPart @Validated DipendenteDTO dipendenteDTO, BindingResult validation) {

        try {

            if (validation.hasErrors()) {
                String message = "Utente non creato";
                for (ObjectError error : validation.getAllErrors()) {
                    message += error.getDefaultMessage() + "\n";
                }
            }
            if (!immagineProfilo.isEmpty()) {
                try {
                    Map mappa = cloudinary.uploader().upload(immagineProfilo.getBytes(), ObjectUtils.emptyMap());
                    String urlImage = (String) mappa.get("secure_url");
                    dipendenteDTO.setImmagineProfilo(urlImage);
                    Long idGenerato = dipendenteService.idDipendente(dipendenteDTO);
                    return dipendenteDTO;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            } else {
                if (dipendenteDTO.getUsername() == null) {
                    throw new RuntimeException("Non puo esistere un dipendente senza username!");
                }
                return dipendenteService.createDipendenteDto(dipendenteDTO);

            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DipendenteDTO modificaDipendente(@RequestBody DipendenteDTO dipendenteDTO, @PathVariable Long id){
        return dipendenteService.modifyDipendente(dipendenteDTO,id);
    }

    @GetMapping("/tuttiidipendenti")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<DipendenteDTO> getAllViaggi(){
        return dipendenteService.getAllDipendenti();

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DipendenteDTO getDipendenteById(@PathVariable Long id){
        return dipendenteService.getDipendenteById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteDipendente(@PathVariable Long id){
        return dipendenteService.deleteDipendente(id);
    }
}