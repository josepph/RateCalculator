#App test

####Summary:
This application resolves the problem proposed in the document "test.txt" which can be found in the same folder as this README.md file.

####Steps to run this test:
- copy and unzip content
- go to the "ratecalculator" folder
- run maven install: 
```
maven clean install
```
- and finally, run the jar with following pattern:
```
java -jar [app.jar] [market data csv file path] [loan amount]
```
Example:
```
..\ratecalculator>java -jar target\ratecalculator-1.0-SNAPSHOT.jar src\main\resources\market_data.csv 1000
```

####Approach:
- this test has been resolved aiming for simplicity and readibility.
- reusability and adaptability to changes are also achieved through modularization.

####Additional comments:
- is possible that the formula applied to obtain the rate and payments is not fully accurate. In this case, we would need to modify only the module where formulas are defined.
- to obtain the final rate, the relative weight of the amount provided by each lender is considered.

####Possible improvements:
- Double has been used to hold decimal values. BigDecimal is another suitable option. Analysis could be done to validate which of the two is better in this case.
- if added possibility of internationalization, a sort of Amount object would be defined, holding currency pattern and currency symbol.
- add logs with key details, in order to be able to follow steps done.
- in case the amount of data to handle (list of lenders, basically) grows to other orders of magnitude, an analysis would be required to ensure the application could cope with that.

####Tests:
- tests in place would highlight code changes that impact incorrectly on results
- many more tests could be added if we were looking to increase the coverage or consider that operations like reading a file from disk could be wrong.
- in case tests need to be launched, it can be done using
```
mvn test
```

####Steps required to productionalize this solution:
- create javadoc
- upload the code into a team shared repository
- ask for peer review and add agreed improvements
- use CI / CD pipeline