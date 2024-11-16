package com.estramipyme.estramipyme_API.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estramipyme.estramipyme_API.Repositories.SectorRepository;
import com.estramipyme.estramipyme_API.models.Sector;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    public List<Sector> getAllSectores() {
        return sectorRepository.findAll();
    }

    public Sector getSectorById(Long id) {
        return sectorRepository.findById(id).orElse(null);
    }

    public Sector saveSector(Sector sector) {
        return sectorRepository.save(sector);
    }

    public Sector updateSector(Long id, Sector sectorDetails) {
        Sector sector = sectorRepository.findById(id).orElse(null);
        if (sector != null) {
            sector.setDescripcionSector(sectorDetails.getDescripcionSector());
            return sectorRepository.save(sector);
        }
        return null;
    }

    public void deleteSector(Long id) {
        sectorRepository.deleteById(id);
    }
}