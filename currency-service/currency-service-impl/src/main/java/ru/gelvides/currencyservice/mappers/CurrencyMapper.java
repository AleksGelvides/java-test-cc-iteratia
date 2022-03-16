package ru.gelvides.currencyservice.mappers;

import dto.ConversationResultDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.gelvides.currencyservice.domain.HistoryConversation;

@Mapper
public interface CurrencyMapper {
    CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);

    ConversationResultDto toConversationResultDto(HistoryConversation historyConversation);
}
