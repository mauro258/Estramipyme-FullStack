package com.estramipyme.estramipyme_API.controllers;

import com.estramipyme.estramipyme_API.models.Sector;
import com.estramipyme.estramipyme_API.services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sectores")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @GetMapping
    public List<Sector> getAllSectores() {
        return sectorService.getAllSectores();
    }

    @GetMapping("/{id}")
    public Sector getSectorById(@PathVariable Long id) {
        return sectorService.getSectorById(id);
    }

    @PostMapping
    public Sector createSector(@RequestBody Sector sector) {
        return sectorService.saveSector(sector);
    }

    @PutMapping("/{id}")
    public Sector updateSector(@PathVariable Long id, @RequestBody Sector sectorDetails) {
        return sectorService.updateSector(id, sectorDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteSector(@PathVariable Long id) {
        sectorService.deleteSector(id);
    }
}