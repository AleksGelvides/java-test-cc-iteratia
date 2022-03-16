package ru.gelvides.currencyservice.mappers;

import dto.HistoryConversationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.gelvides.currencyservice.domain.Currency;
import ru.gelvides.currencyservice.domain.HistoryConversation;

import java.time.LocalDate;

@Mapper
public interface HistoryMapper {
    HistoryMapper INSTANCE = Mappers.getMapper(HistoryMapper.class);

    default HistoryConversationDto toConversationResultDto
            (HistoryConversation historyConversation){
        return new HistoryConversationDto()
                .setId(historyConversation.getId())
                .setBaseName(historyConversation.getBaseCurrency().getCharcode())
                .setTargetName(historyConversation.getTargetCurrency().getCharcode())
                .setDateConversation(historyConversation.getDateConversation())
                .setCourse(historyConversation.getCourse());
    }

    default HistoryConversation createHistoryConversation(Currency baseCurrency,
                                                  Currency targetCurrency,
                                                  LocalDate date,
                                                  double crossCourse){
        return new HistoryConversation()
                .setBaseCurrency(baseCurrency)
                .setTargetCurrency(targetCurrency)
                .setCourse(crossCourse)
                .setDateConversation(date);
    }
}
