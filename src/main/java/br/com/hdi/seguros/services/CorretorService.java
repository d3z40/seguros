package br.com.hdi.seguros.services;

import br.com.hdi.seguros.error.ResourceNotAtivoException;
import br.com.hdi.seguros.error.ResourceNotFoundException;
import br.com.hdi.seguros.models.Corretor;
import br.com.hdi.seguros.repositories.CorretorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CorretorService {

    private final CorretorRepository corretorRepository;

    public ResponseEntity<Page<Corretor>> findAllCorretorByAtivoTrue(Pageable pageable) {
        // Não foi detalhado se deveria retornar todos ou apenas os ativos.
        return new ResponseEntity<>(corretorRepository.findCorretorByAtivoTrue(pageable), HttpStatus.OK);
    }

    public ResponseEntity<Page<Corretor>> findAll(Pageable pageable) {
        // Criei para retornar todos, sem filtro para o item 2
        return new ResponseEntity<>(corretorRepository.findAll(pageable), HttpStatus.OK);
    }

    public ResponseEntity<Optional<Corretor>> findByDocumentoAtivo(String documento) {
        Optional<Corretor> corretor = corretorRepository.findCorretorByDocumento(documento);

        if (corretor.isPresent() && corretor.get().isAtivo()) {
            return new ResponseEntity<>(corretor, HttpStatus.OK);
        } else if (corretor.isPresent() && !corretor.get().isAtivo()) {
            throw new ResourceNotAtivoException("Documento: " + documento + " não está ativo.");
        } else {
            throw new ResourceNotFoundException("Documento: " + documento + " não encontrado.");
        }
    }

    public ResponseEntity<Optional<Corretor>> findByDocumento(String documento) {
        // Para item 2, foi retirado a validação de ativos
        return new ResponseEntity<>(corretorRepository.findCorretorByDocumento(documento), HttpStatus.OK);
    }

    public ResponseEntity<Corretor> save(Corretor Corretor) {
        return new ResponseEntity<>(corretorRepository.save(Corretor), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Corretor> update(String documento) {
        return corretorRepository.findCorretorByDocumento(documento)
                .map(newCorretor -> {
                    newCorretor.setDocumento(documento);

                    return ResponseEntity.accepted().body(corretorRepository.save(newCorretor));
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> delete(String documento) {
        return corretorRepository.findCorretorByDocumento(documento)
                .map(deleteCorretor -> {
                    corretorRepository.delete(deleteCorretor);

                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}