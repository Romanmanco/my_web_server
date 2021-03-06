package com.example.my_web_server.botapi;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

@Component
public class Weather {

    public static String getWeather(String message, Model model) throws IOException {

        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=66d6f86f07d7649ae431460ad798f6c7");
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));
        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setFeels(main.getDouble("feels_like"));
        model.setHumidity(main.getDouble("humidity"));
        JSONObject wind = object.getJSONObject("wind");
        model.setWindSpeed(wind.getDouble("speed"));
        JSONArray getArray = object.getJSONArray("weather");

        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));

        }

        return "Город: " + model.getName() + "\n"
                + "Температура: " + model.getTemp() + "C" + "\n"
                + "Ощущается: " + model.getFeels() + "C" + "\n"
                + "Скорость ветра: " + model.getWindSpeed() + "М/с" + "\n"
                + "Влажность: " + model.getHumidity() + "%" + "\n"
                + "Небо: " + model.getMain() + "\n"
                + "http://openweathermap.org/img/w/" + model.getIcon() + ".png";
    }
}