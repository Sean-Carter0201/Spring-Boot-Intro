package com.tsi.training.Carter.Sean.SpringTrainingExample;

import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
