package com.cke.marketapp.repository;

import com.cke.marketapp.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
