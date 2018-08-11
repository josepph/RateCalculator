import com.jp.ratecalculator.domain.Lender;
import com.jp.ratecalculator.domain.Quote;
import com.jp.ratecalculator.exception.IncorrectLoanAmount;
import com.jp.ratecalculator.service.DataLoaderService;
import com.jp.ratecalculator.service.QuoteService;
import com.jp.ratecalculator.service.ValidatorService;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.List;

import static com.jp.ratecalculator.configuration.Constants.*;
import static org.junit.Assert.assertEquals;

public class LoanRateCalculatorTest {

    private ValidatorService validatorService = new ValidatorService();
    private Quote obtainedQuote;

    DecimalFormat repaymentFormat = new DecimalFormat(REPAYMENT_FORMAT);
    DecimalFormat rateFormat = new DecimalFormat(RATE_FORMAT);


    @Before
    public void setUp() throws Exception {

        String filePathName = getClass().getClassLoader().getResource("test_market_data.csv").getPath();

        DataLoaderService dataLoaderService = new DataLoaderService();
        List<Lender> sortedLendersList = dataLoaderService.loadMarketDataFile(filePathName);

        obtainedQuote = new QuoteService().obtainQuote(
                sortedLendersList,
                1000,
                LOAN_NUMBER_OF_MONTHS);

    }


    @Test
    public void obtainLoanRateTest() {

        assertEquals("7,2%", rateFormat.format(obtainedQuote.getResultingRate()));

    }


    @Test
    public void obtainMonthlyRepaymentTest() {

        assertEquals("34,22", repaymentFormat.format(obtainedQuote.getMonthlyRepayment()));

    }


    @Test
    public void obtainTotalRepaymentTest() {

        assertEquals("1231,93", repaymentFormat.format(obtainedQuote.getTotalRepayment()));

    }


    @Test(expected = IncorrectLoanAmount.class)
    public void tooLowerAmountTest() throws Exception{

        String testFilePath = getClass().getClassLoader().getResource("test_market_data.csv").getPath();
        String[] testArgs = new String[]{testFilePath, "100"};

        validatorService.validInputParameters(testArgs);

    }


    @Test(expected = IncorrectLoanAmount.class)
    public void tooHighAmountTest() throws Exception{

        String testFilePath = getClass().getClassLoader().getResource("test_market_data.csv").getPath();
        String[] testArgs = new String[]{testFilePath, "20000"};

        validatorService.validInputParameters(testArgs);

    }


    @Test(expected = IncorrectLoanAmount.class)
    public void wrongIncrementTest() throws Exception{

        String testFilePath = getClass().getClassLoader().getResource("test_market_data.csv").getPath();
        String[] testArgs = new String[]{testFilePath, "4150"};

        validatorService.validInputParameters(testArgs);

    }


}
