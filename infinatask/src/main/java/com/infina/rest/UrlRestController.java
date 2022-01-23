package com.infina.rest;

import com.infina.model.ShortenUrlModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class UrlRestController {

    private Map<String, ShortenUrlModel> shortenUrlList = new HashMap<>();

    @RequestMapping(value="/shortenurl", method= RequestMethod.POST)
    public ResponseEntity<Object> getShortenUrl(@RequestBody ShortenUrlModel shortenUrl) throws MalformedURLException {
        String randomChar = getRandomChars();
        setShortUrl(randomChar, shortenUrl);
        return new ResponseEntity<Object>(shortenUrl, HttpStatus.OK);
    }

    @RequestMapping(value="/s/{randomstring}", method=RequestMethod.GET)
    public void getFullUrl(HttpServletResponse response, @PathVariable("randomstring") String randomString) throws IOException {
        response.sendRedirect(shortenUrlList.get(randomString).getFull_url());
    }

    private void setShortUrl(String randomChar, ShortenUrlModel shortenUrl) throws MalformedURLException {
        shortenUrl.setShort_url("http://localhost:8080/s/"+randomChar);
        shortenUrlList.put(randomChar, shortenUrl);
    }

    private String getRandomChars() {
        String randomStr = "";
        String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 5; i++)
            randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));
        return randomStr;
    }

}