package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
