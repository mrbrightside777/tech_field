package com.example.googleplacesassignment.Misc;

public class Constants {
    public static class WEB {
        public static String GOOGLE_MAPS_BASE = "https://maps.googleapis.com";
        public static String GOOGLE_PLACES_BASE = "";
        public static String GOOGLE_AUTCOMPLETE_BASE = String.format("%s%s", GOOGLE_MAPS_BASE, "/maps/api/place/autocomplete/json");
        public static String GOOGLE_API_KEY = "AIzaSyA3FCkpr7v_-rwlgWF_YhHwlzNrwl7GlMI";
        public static int LOCATION_SEARCH_RADIUS = 1000;
        public static String PLACE_INPUT_TYPE="textquery";
        public static String PLACE_FIELDS = "photos,formatted_address,name,rating,opening_hours,geometry";
    }
}
