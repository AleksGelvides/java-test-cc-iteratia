package ru.gelvides.currencyservice.repositories;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gelvides.currencyservice.domain.Currency;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency getByCharcode(String charCode);

    Currency getByCharcodeAndDate(String charCode, LocalDate date);

    List<Currency> findByDate(LocalDate date);

    @Query(value = "select distinct date from Currency where date= :date", nativeQuery = true)
    LocalDate dateSearch(@Param("date") LocalDate date);
}
