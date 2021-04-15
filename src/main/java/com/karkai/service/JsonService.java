package com.karkai.service;

import com.amazonaws.util.IOUtils;
import net.minidev.json.parser.ParseException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.IOException;
import java.net.URL;

public class JsonService {

    public void jsonParse(String url) throws IOException, ParseException {
        JSONObject jo = (JSONObject) new JSONTokener(IOUtils.toString(new URL(url).openStream())).nextValue();
        System.out.println(jo.get("question1"));
    }
}
