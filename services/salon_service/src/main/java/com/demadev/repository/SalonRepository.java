package com.demadev.repository;

import com.demadev.model.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {
    Salon findByOwnerId(long ownerId);

    List<Salon> findAllByCity(String city);

    @Query(
            "SELECT s FROM Salon s WHERE" +
                    "(lower(s.city) LIKE lower(concat('%', :keyword, '%')) OR " +
                    "lower(s.name) LIKE lower(concat('%', :keyword, '%')) OR " +
                    "lower(s.address) LIKE lower(concat('%', :keyword, '%')))"
    )
    List<Salon> searchSalon(@Param("keyword") String keyword);
}
