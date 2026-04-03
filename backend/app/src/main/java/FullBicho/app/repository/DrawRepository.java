package FullBicho.app.repository;

import FullBicho.app.entity.Draw;
import FullBicho.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrawRepository extends JpaRepository<Draw, Long> {

}
