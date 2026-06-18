package com.socorroandino.service_rescates.service;

import com.socorroandino.service_rescates.model.ReporteRescates;
import com.socorroandino.service_rescates.repository.ReporteRescatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteRescatesService {

    private final ReporteRescatesRepository repository;

    public ReporteRescates guardarReporte(ReporteRescates reporte) {
        return repository.save(reporte);
    }

    public List<ReporteRescates> listarHistorial() {
        return repository.findAll();
    }
}