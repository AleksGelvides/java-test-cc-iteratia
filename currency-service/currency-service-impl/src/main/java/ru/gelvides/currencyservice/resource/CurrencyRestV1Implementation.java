package ru.gelvides.currencyservice.resource;

import dto.HistoryConversationDto;
import dto.StatisticConversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import resources.v1.CurrencyRestV1;
import ru.gelvides.currencyservice.service.CurrencyServiceImpl;

import java.util.*;

@RestController
public class CurrencyRestV1Implementation implements CurrencyRestV1 {
    @Autowired
    private CurrencyServiceImpl service;

    @Override
    public ResponseEntity<?> conversation(String base, String target, double quantity) {
        try{
            var result = service.conversation(base, target, quantity);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getContent() {
        try{
            List<HistoryConversationDto> history = service.getHistoryConversation();
            List<StatisticConversation> statisticConversations = service.getStatistic();
            Map<String, Collection> response = new HashMap<>();
            response.put("History", history);
            response.put("Statistic", statisticConversations);
            return new ResponseEntity<>(response ,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
