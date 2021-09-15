package br.com.hdi.seguros.controllers;

import br.com.hdi.seguros.models.Corretor;
import br.com.hdi.seguros.services.CorretorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/broker")
@CrossOrigin("*")
public class CorretorController {

    private final CorretorService corretorService;

    @GetMapping
    public ResponseEntity<Page<Corretor>> getAllCorretores(@PageableDefault(sort = "id") Pageable pageable) {
        return corretorService.findAllCorretorByAtivoTrue(pageable);
    }

    @GetMapping("/{documento}")
    public  ResponseEntity<Optional<Corretor>> getCorretorByDocumento(@PathVariable("documento") String documento) {
        return corretorService.findByDocumentoAtivo(documento);
    }

    @PostMapping
    public ResponseEntity<Corretor> save(@RequestBody @Valid Corretor Corretor) {
        return corretorService.save(Corretor);
    }

    @PutMapping
    public ResponseEntity<Corretor> update(@RequestBody @Valid String documento) {
        return corretorService.update(documento);
    }

    @DeleteMapping("{documento}")
    public ResponseEntity<Object> delete(@PathVariable("id") String documento) {
        return corretorService.delete(documento);
    }
}