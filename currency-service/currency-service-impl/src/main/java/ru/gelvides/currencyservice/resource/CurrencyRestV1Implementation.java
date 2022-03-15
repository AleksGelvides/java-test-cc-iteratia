package ru.gelvides.currencyservice.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import resources.v1.CurrencyRestV1;

@RestController
public class CurrencyRestV1Implementation implements CurrencyRestV1 {

    @Override
    public ResponseEntity<?> getExchangeCourse(String base, String target, double quantity) {
        return null;
    }

    @Override
    public ResponseEntity<?> getHistory() {
        return null;
    }

    @Override
    public ResponseEntity<?> getStatistic() {
        return null;
    }
}
