package com.example.mvvmgithub.Misc;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static class WEB {
        public static final String GITHUB_BASE_URL = "https://api.github.com/";
        public static final String ACCESS_TOKEN = "efc8bdfb64ece0af6bd93fd8a3661bf09ab675b5";
        public static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.75 Mobile Safari/537.36";
        public static Map<String, String> headers = makeHeaders();

        public static Map<String, String> makeHeaders() {
            Map<String, String> map = new HashMap<>();
            map.put("User-Agent", USER_AGENT);
            map.put("Application", ACCESS_TOKEN);
            return map;
        }
    }
    public static class DB {
        public static final int VERSION_NUMBER = 2;
        public static final String DB_NAME = "Github.db";
    }
}
