package com.epicode.ViaggiAziendali.mapper;

import com.epicode.ViaggiAziendali.entity.Prenotazione;
import com.epicode.ViaggiAziendali.payload.DipendenteDTO;
import com.epicode.ViaggiAziendali.payload.PrenotazioneDTO;
import org.springframework.stereotype.Component;

@Component
public class PrenotazioneMapperDTO {


    public static PrenotazioneDTO toPrenotazioneDTO(Prenotazione prenotazione){
        PrenotazioneDTO prenotazioneDTO = new PrenotazioneDTO();
        prenotazioneDTO.setDataRichiesta(prenotazione.getDataRichiesta());
        prenotazioneDTO.setDipendente(prenotazione.getDipendente());
        prenotazioneDTO.setViaggio(prenotazione.getViaggio());

        return prenotazioneDTO;
    }

    public static Prenotazione toPrenotazioneEntity(PrenotazioneDTO prenotazioneDTO){
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataRichiesta(prenotazioneDTO.getDataRichiesta());
        prenotazione.setDipendente(prenotazioneDTO.getDipendente());
        prenotazione.setViaggio(prenotazioneDTO.getViaggio());

        return prenotazione;
    }
}