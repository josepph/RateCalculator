import com.jp.ratecalculator.domain.Lender;
import com.jp.ratecalculator.domain.Quote;
import com.jp.ratecalculator.service.QuoteService;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.jp.ratecalculator.configuration.Constants.*;
import static org.junit.Assert.assertEquals;

public class QuoteServiceTest {

    QuoteService quoteService = new QuoteService();
    List<Lender> lenders = new ArrayList<>();


    @Before
    public void setUp() {

        // for simplicity, we set the list of lenders already sorted by 'offeredRate'.
        // if this was not the case, we would need to apply Collection.sort
        lenders.add(new Lender("Marcy", 0.069, 310));
        lenders.add(new Lender("Mary", 0.072, 450));
        lenders.add(new Lender("Ann", 0.075, 200));
        lenders.add(new Lender("Josep", 0.08, 500));
        lenders.add(new Lender("John", 0.081, 150));

    }


    @Test
    public void ObtainQuoteTest() {

        DecimalFormat repaymentFormat = new DecimalFormat(REPAYMENT_FORMAT);
        DecimalFormat rateFormat = new DecimalFormat(RATE_FORMAT);

        Quote quote = quoteService.obtainQuote(lenders, 1100, 36);

        assertEquals("7,3%", rateFormat.format(quote.getResultingRate()));
        assertEquals("37,75", repaymentFormat.format(quote.getMonthlyRepayment()));
        assertEquals("1358,91", repaymentFormat.format(quote.getTotalRepayment()));

    }


}
