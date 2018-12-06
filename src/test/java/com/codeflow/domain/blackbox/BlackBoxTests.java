package com.codeflow.domain.blackbox;

import com.codeflow.application.client.ClientFacadePackingService;
import com.codeflow.application.client.Result;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BlackBoxTests {


    String pack(String file) throws IOException {
        ClientFacadePackingService clientFacadePackingService = new ClientFacadePackingService();
        Result pack = clientFacadePackingService.pack(Paths.get("./src/test/resources/input/" + file));
        return pack.toString();
    }

    String output(String file) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("./src/test/resources/packedpositions/" + file));
        return new String(bytes);
    }

    void assertContent(String fileName) throws IOException {
        String pack = pack(fileName);
        String output = output(fileName);
        Assert.assertEquals(output, pack);
    }

    @Test
    public void dpp01() throws IOException {
        assertContent("dpp01.txt");
    }

    @Test
    public void dpp02() throws IOException {
        assertContent("dpp02.txt");
    }

    @Test
    public void dpp03() throws IOException {
        assertContent("dpp03.txt");
    }

    @Test
    public void dpp04() throws IOException {
        assertContent("dpp04.txt");
    }

    @Test
    public void dpp05() throws IOException {
        assertContent("dpp05.txt");
    }

    @Test
    public void dpp06() throws IOException {
        assertContent("dpp06.txt");
    }

    @Test
    public void mpp01() throws IOException {
        assertContent("mpp01.txt");
    }

    @Test
    public void mpp02() throws IOException {
        assertContent("mpp02.txt");
    }

    @Test
    public void mpp03() throws IOException {
        assertContent("mpp03.txt");
    }

    @Test
    public void mpp04() throws IOException {
        assertContent("mpp04.txt");
    }

    @Test
    public void mpp05() throws IOException {
        assertContent("mpp05.txt");
    }

    @Test
    public void rnd01() throws IOException {
        assertContent("rnd01.txt");
    }

    @Test
    public void rnd02() throws IOException {
        assertContent("rnd02.txt");
    }

    @Test
    public void rnd03() throws IOException {
        assertContent("rnd03.txt");
    }

    @Test
    public void rnd04() throws IOException {
        assertContent("rnd04.txt");
    }

    @Test
    public void rnd05() throws IOException {
        assertContent("rnd05.txt");
    }
}
