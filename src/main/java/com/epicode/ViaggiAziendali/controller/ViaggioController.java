package com.epicode.ViaggiAziendali.controller;

import com.epicode.ViaggiAziendali.payload.ViaggioDTO;
import com.epicode.ViaggiAziendali.service.ViaggioService;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    Cloudinary cloudinary;
    @Autowired
    ViaggioService viaggioService;


    @PostMapping("/newviaggio")
    @ResponseStatus(HttpStatus.CREATED)
    public ViaggioDTO createNewViaggio(@RequestBody @Validated ViaggioDTO viaggioDTO){
        if(viaggioDTO.getDestinazione() == null){
            throw new RuntimeException("Non puoi creare un viaggio senza una data⚠️");
        }
        return viaggioService.createViaggioDto(viaggioDTO);
    }

    @GetMapping("/tuttiiviaggi")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ViaggioDTO> getAllViaggi(){
        return viaggioService.getAllViaggi();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ViaggioDTO getViaggioById(@PathVariable Long id){
        return viaggioService.getViaggioById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteViaggio(@PathVariable Long id){
        return viaggioService.deleteViaggio(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ViaggioDTO modifyViaggio(@RequestBody ViaggioDTO viaggioDTO, @PathVariable Long id){
        return viaggioService.modifyViaggio(viaggioDTO,id);
    }

}