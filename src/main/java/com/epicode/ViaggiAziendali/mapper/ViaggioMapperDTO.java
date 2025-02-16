package com.epicode.ViaggiAziendali.mapper;

import com.epicode.ViaggiAziendali.entity.Viaggio;
import com.epicode.ViaggiAziendali.payload.ViaggioDTO;
import org.springframework.stereotype.Component;

@Component
public class ViaggioMapperDTO {


    public static ViaggioDTO toViaggioDTO (Viaggio viaggio){
        ViaggioDTO viaggioDTO = new ViaggioDTO();
        viaggioDTO.setDataViaggio(viaggio.getDataViaggio());
        viaggioDTO.setStato(viaggio.getStato());
        viaggioDTO.setDestinazione(viaggio.getDestinazione());
        return viaggioDTO;
    }


    public static Viaggio toViaggioEntity(ViaggioDTO viaggioDTO){
        Viaggio viaggio = new Viaggio();
        viaggio.setDataViaggio(viaggioDTO.getDataViaggio());
        viaggio.setStato(viaggioDTO.getStato());
        viaggio.setDestinazione(viaggioDTO.getDestinazione());
        return viaggio;
    }
}