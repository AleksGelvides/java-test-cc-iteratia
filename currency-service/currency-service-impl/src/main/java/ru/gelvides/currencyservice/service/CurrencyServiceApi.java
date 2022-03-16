package ru.gelvides.currencyservice.service;

import dto.ConversationResultDto;
import dto.HistoryConversationDto;
import dto.StatisticConversation;
import ru.gelvides.currencyservice.domain.HistoryConversation;

import java.util.List;

public interface CurrencyServiceApi {

    ConversationResultDto conversation (String base, String target, double count);

    List<HistoryConversationDto> getHistoryConversation();

    List<StatisticConversation> getStatistic ();
}
