package com.epicode.ViaggiAziendali.payload;

import com.epicode.ViaggiAziendali.entity.Dipendente;
import com.epicode.ViaggiAziendali.entity.Viaggio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneDTO {

    @NotNull(message = "il campo 'dataRichiesta' non può essere rappresentataa da soli spazi vuoti!")
    private LocalDate dataRichiesta;
    @NotNull(message = "il campo 'dipendente' non può essere rappresentataa da soli spazi vuoti!")
    private Dipendente dipendente;
    @NotNull(message = "il campo 'viaggio' non può essere rappresentataa da soli spazi vuoti!")
    private Viaggio viaggio;

}