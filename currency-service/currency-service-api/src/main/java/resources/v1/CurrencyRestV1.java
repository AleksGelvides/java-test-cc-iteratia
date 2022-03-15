package resources.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/currency/v1/")
public interface CurrencyRestV1 {

    @GetMapping("exchange/{base}/{target}/{quantity}")
    ResponseEntity<?> getExchangeCourse(@PathVariable String base,
                                        @PathVariable String target,
                                        @PathVariable double quantity);

    @GetMapping("history")
    ResponseEntity<?> getHistory();

    @GetMapping("statistic")
    ResponseEntity<?> getStatistic();
}
