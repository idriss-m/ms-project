package tn.esprit.springboot;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends MongoRepository<Classe, Long> {

}
