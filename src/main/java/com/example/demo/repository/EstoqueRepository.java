package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

}
