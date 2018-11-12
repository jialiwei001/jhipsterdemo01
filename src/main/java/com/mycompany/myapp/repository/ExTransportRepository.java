package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ExTransport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the ExTransport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExTransportRepository extends JpaRepository<ExTransport, Long> {

    List<ExTransport> findByEx_extransport(String eeeeexxxx);
    int a = 10;

}
