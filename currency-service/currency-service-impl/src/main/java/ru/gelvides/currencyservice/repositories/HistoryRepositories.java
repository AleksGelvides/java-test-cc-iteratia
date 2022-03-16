package ru.gelvides.currencyservice.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gelvides.currencyservice.domain.Currency;
import ru.gelvides.currencyservice.domain.HistoryConversation;

import java.time.LocalDate;
import java.util.List;

@Qualifier("history")
public interface HistoryRepositories extends JpaRepository<HistoryConversation, Long> {

    List<HistoryConversation> findByBaseCurrency(Currency currency);

    List<HistoryConversation> findByDateConversationAfter(LocalDate date);

}
