package com.jp.ratecalculator.service;

import com.jp.ratecalculator.domain.Lender;

import java.io.*;
import java.util.*;

public class DataLoaderService {

    // these are specific constants for this loading process, so not included in the Constants class
    private static final String SEPARATOR = ",";
    private static final Integer NAME_COLUMN = 0;
    private static final Integer RATE_COLUMN = 1;
    private static final Integer AVAILABLE_COLUMN = 2;


    public List<Lender> loadMarketDataFile(String filePath) throws IOException {

        List<Lender> lendersList = new ArrayList<>();

        FileInputStream fileStream = new FileInputStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));

        // discard first line to skip column descriptions
        br.readLine();

        String strLine;
        while ((strLine = br.readLine()) != null)   {

            lendersList.add(stringToLender(strLine));

        }
        br.close();

        // we sort the list by offered rate
        Collections.sort(lendersList, Comparator.comparingDouble(Lender::getOfferedRate));

        return lendersList;

    }


    private Lender stringToLender(String line) {

        String splitedLine[] = line.split(SEPARATOR);

        String name = splitedLine[NAME_COLUMN].trim();
        Double rate = Double.parseDouble(splitedLine[RATE_COLUMN].trim());
        Integer available = Integer.parseInt(splitedLine[AVAILABLE_COLUMN].trim());

        return new Lender(name, rate, available);

    }


}
