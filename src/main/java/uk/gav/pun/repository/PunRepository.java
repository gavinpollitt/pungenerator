package uk.gav.pun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.gav.pun.entity.Pun;


public interface PunRepository  extends JpaRepository<Pun, Integer> {

}
