package ru.gelvides.currencyservice.service;

import dto.ConversationResultDto;
import dto.HistoryConversationDto;
import dto.StatisticConversation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.gelvides.currencyservice.feign.CbrFeignClient;
import ru.gelvides.currencyservice.mappers.HistoryMapper;
import ru.gelvides.currencyservice.repositories.CurrencyRepository;
import ru.gelvides.currencyservice.repositories.HistoryRepositories;
import ru.gelvides.currencyservice.domain.Currency;
import ru.gelvides.currencyservice.domain.HistoryConversation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class CurrencyServiceImpl implements CurrencyServiceApi {
    private final CurrencyRepository currencyRepository;
    private final HistoryRepositories historyRepository;
    private final CbrFeignClient cbrFeignClient;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository,
                               HistoryRepositories historyRepository,
                               CbrFeignClient cbrFeignClient) {
        this.currencyRepository = currencyRepository;
        this.historyRepository = historyRepository;
        this.cbrFeignClient = cbrFeignClient;
    }

    @Override
    public ConversationResultDto conversation(String base, String target, double count) {
        if (currencyRepository.dateSearch(LocalDate.now()) == null)
            init();
        var baseCurrency = currencyRepository.getByCharcodeAndDate(base, LocalDate.now());
        var targetCurrency = currencyRepository.getByCharcodeAndDate(target, LocalDate.now());
        var crossCourse = baseCurrency.getValue() / targetCurrency.getValue();
        HistoryConversation historyConversation =
                HistoryMapper.INSTANCE.createHistoryConversation
                        (baseCurrency, targetCurrency, LocalDate.now(), crossCourse);
        historyRepository.save(historyConversation);
        return new ConversationResultDto(count * crossCourse);
    }

    @Override
    public List<HistoryConversationDto> getHistoryConversation() {
        return historyRepository.
                findByDateConversationAfter(LocalDate.now().minusDays(7))
                .stream()
                .map(HistoryMapper.INSTANCE::toConversationResultDto).toList();
    }

    @Override
    public List<StatisticConversation> getStatistic() {
        List<StatisticConversation> statistic = new ArrayList<>();
        Map<String, List<HistoryConversation>> histories =
                historyRepository.findByDateConversationAfter(LocalDate.now().minusDays(7))
                        .stream()
                        .collect(Collectors
                                .groupingBy(historyConversation -> historyConversation.getBaseCurrency().getCharcode()));
        for (Map.Entry<String, List<HistoryConversation>> value : histories.entrySet()) {
            statistic.add(new StatisticConversation()
                    .setBase(value.getKey())
                    .setTarget(value.getValue().get(0).getTargetCurrency().getCharcode())
                    .setCountConversation(value.getValue().size())
                    .setAverageCourse(value.getValue().stream().mapToDouble(HistoryConversation::getCourse).average().getAsDouble()));
        }
        return statistic.stream().sorted().toList();
    }

    private void init() {
        try {
            String response = cbrFeignClient.getAllCurrency();
            DocumentBuilder documentBuilder = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder();
            Document document = documentBuilder.parse(
                    new ByteArrayInputStream(response.getBytes("windows-1251")));
            NodeList valutes = document.getElementsByTagName("Valute");
            IntStream.range(0, valutes.getLength())
                    .forEach(i -> {
                        Element element = (Element) valutes.item(i);
                        var currency = new Currency()
                                .setNumcode(element.getElementsByTagName("NumCode").item(0).getTextContent())
                                .setCharcode(element.getElementsByTagName("CharCode").item(0).getTextContent())
                                .setNominal(Integer.parseInt(element.getElementsByTagName("Nominal").item(0).getTextContent()))
                                .setName(element.getElementsByTagName("Name").item(0).getTextContent())
                                .setValue(Double.parseDouble(
                                        element.getElementsByTagName("Value")
                                                .item(0).getTextContent()
                                                .replace(',', '.')))
                                .setDate(LocalDate.now());
                        currencyRepository.save(currency);
                    });
            log.info("Все валюты сохранены в базу данных");

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
