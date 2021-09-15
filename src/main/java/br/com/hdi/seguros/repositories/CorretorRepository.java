package br.com.hdi.seguros.repositories;

import br.com.hdi.seguros.models.Corretor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorretorRepository extends MongoRepository<Corretor, String> {

    Page<Corretor> findCorretorByAtivoTrue(Pageable pageable);
    Optional<Corretor> findCorretorByDocumento(String documento);
}