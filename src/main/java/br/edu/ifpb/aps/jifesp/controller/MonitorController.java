package br.edu.ifpb.aps.jifesp.controller;

import br.edu.ifpb.aps.jifesp.entity.MonitorEntity;
import br.edu.ifpb.aps.jifesp.service.MonitorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monitores")
public class MonitorController {
    private final MonitorService monitorService;

    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @PostMapping
    public MonitorEntity salvarMonitor(@RequestBody MonitorEntity monitorEntity) {
        return monitorService.save(monitorEntity);
    }

    @PutMapping("/{id}")
    public MonitorEntity atualizarMonitor(@PathVariable Long id, @RequestBody MonitorEntity monitorEntity) {
        return monitorService.update(id, monitorEntity);
    }

    @DeleteMapping("/{id}")
    public void deletarMonitor(@PathVariable Long id) {
        monitorService.delete(id);
    }

    @GetMapping
    public List<MonitorEntity> listarMonitores() {
        return monitorService.findAll();
    }

}
