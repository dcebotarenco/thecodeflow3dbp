package com.codeflow.application;

import com.codeflow.application.client.ClientFacadePackingService;
import com.codeflow.application.client.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("Starting..");

        ClientFacadePackingService clientFacadePackingService = new ClientFacadePackingService();
        Result result = clientFacadePackingService.pack(Paths.get("./src/test/resources/input/rnd05.txt"));
        LOGGER.info("");
        LOGGER.info("{}", result);
    }

}
