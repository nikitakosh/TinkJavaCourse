package edu.hw6;

import edu.hw6.task5.HackerNews;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HackerNewsTest {

    @Test
    @DisplayName("hacker news top stories")
    public void hackerNewsTopStories() {
        HttpResponse<String> response = Mockito.mock(HttpResponse.class);
        HttpClient client = Mockito.mock(HttpClient.class);
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI("https://hacker-news.firebaseio.com/v0/topstories.json"))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String responseBody = "[38282166,38282950,38286299,38285589,38276743,38286202,38285724,38279459,38283860,38276680,38277361,38278171,38283936,38273488,38274642,38285228,38286130,38284196,38285482,38280472,38277926,38280201,38277248,38275792,38281848,38282728,38285251,38278246,38281137,38274923,38275487,38276075,38282563,38286242,38283504,38280664,38277701,38285594,38280490,38285361,38275791,38284150,38273947,38280974,38281079,38266773,38274714,38283230,38276657,38279073,38274782,38271155,38279765,38264641,38276951,38277863,38281898,38259148,38276518,38277598,38279364,38274568,38271702,38285764,38277412,38257794,38284141,38270714,38261982,38273392,38281814,38271534,38257094,38278380,38274713,38273999,38281264,38280937,38279732,38283564,38278037,38283417,38281990,38284862,38270265,38281135,38262628,38283591,38272162,38265773,38275384,38282336,38272234,38271959,38272495,38283421,38273608,38275925,38261539,38283926,38259991,38262425,38283788,38262251,38281433,38279217,38279338,38266923,38280936,38278753,38255808,38266387,38282068,38284455,38268958,38279626,38267739,38277105,38283918,38261913,38256137,38269453,38266340,38277058,38278245,38262900,38263786,38285419,38283824,38262775,38264174,38263779,38270446,38281510,38274653,38259146,38275841,38279412,38263118,38275614,38260248,38259100,38283144,38270473,38280594,38272557,38282097,38256810,38272377,38262320,38277138,38268737,38283812,38274872,38262124,38283762,38274241,38255230,38275060,38254353,38256645,38265714,38270084,38271795,38275556,38270936,38272132,38262196,38263435,38263294,38270552,38257024,38278083,38256620,38277855,38262211,38279307,38257813,38268271,38263869,38271758,38266912,38282575,38280100,38255455,38278657,38272226,38263934,38268706,38256577,38269144,38267084,38254153,38268890,38273295,38265183,38284602,38272129,38254835,38262315,38253384,38260069,38279759,38273948,38276209,38266011,38275216,38280382,38269460,38257284,38265857,38264196,38266792,38279009,38254656,38263621,38275542,38269959,38266216,38281451,38263175,38262949,38273624,38254386,38283929,38258101,38267879,38269656,38278338,38255004,38275645,38256392,38280883,38253425,38281864,38271775,38255884,38264876,38269754,38261524,38265088,38255609,38275816,38255496,38273097,38275555,38269737,38271450,38256558,38264269,38255157,38274210,38270649,38262120,38283833,38281895,38264906,38254405,38283399,38254265,38269744,38277220,38268256,38266867,38258880,38269265,38279912,38261415,38261232,38258129,38281316,38266907,38257118,38269650,38265390,38263909,38268803,38260658,38267526,38267740,38256516,38254291,38253315,38270529,38270360,38282969,38270325,38272993,38259204,38272738,38261797,38253406,38278966,38266131,38264937,38266569,38260815,38264742,38277798,38267787,38263051,38271705,38260217,38266144,38263386,38265665,38271216,38265092,38271030,38276810,38270904,38255001,38254866,38264118,38266833,38275554,38268462,38269297,38278350,38254435,38268376,38270044,38255923,38258381,38256166,38258778,38255892,38255404,38272804,38253414,38254786,38260555,38267588,38276741,38272165,38275557,38258015,38255168,38255056,38269475,38258671,38253699,38257182,38257860,38257816,38257310,38274427,38265884,38253434,38256694,38256509,38257049,38256206,38277201,38278074,38254719,38258958,38258718,38262536,38274212,38283562,38267616,38267020,38259727,38257045,38270323,38256707,38261187,38255834,38255789,38269897,38269747,38254589,38266934,38254080,38253937,38253749,38270817,38253521,38253364,38262182,38277774,38275874,38253825,38281944,38261914,38264580,38254040,38261666,38276883,38256951,38257457,38253582,38264687,38268470,38258078,38254138,38255079,38254258,38273223,38286163,38257637,38277430,38255692,38254502,38272845,38285730,38268043,38264778,38254086,38278786,38257141,38259436,38257000,38263833,38253715,38254096,38269445,38260982,38266042,38270582,38259638,38256746,38273528,38269175,38280479,38258842,38253787,38259220,38258406,38279375]";
        Mockito.when(response.body()).thenReturn(responseBody);
        try {
            Mockito.when(client.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        HackerNews hackerNews = new HackerNews(client, response);
        Assertions.assertArrayEquals(hackerNews.hackerNewsTopStories(), new long[] {
                38282166,38282950,38286299,38285589,38276743,38286202,38285724,38279459,38283860,38276680,38277361,38278171,38283936,38273488,38274642,38285228,38286130,38284196,38285482,38280472,38277926,38280201,38277248,38275792,38281848,38282728,38285251,38278246,38281137,38274923,38275487,38276075,38282563,38286242,38283504,38280664,38277701,38285594,38280490,38285361,38275791,38284150,38273947,38280974,38281079,38266773,38274714,38283230,38276657,38279073,38274782,38271155,38279765,38264641,38276951,38277863,38281898,38259148,38276518,38277598,38279364,38274568,38271702,38285764,38277412,38257794,38284141,38270714,38261982,38273392,38281814,38271534,38257094,38278380,38274713,38273999,38281264,38280937,38279732,38283564,38278037,38283417,38281990,38284862,38270265,38281135,38262628,38283591,38272162,38265773,38275384,38282336,38272234,38271959,38272495,38283421,38273608,38275925,38261539,38283926,38259991,38262425,38283788,38262251,38281433,38279217,38279338,38266923,38280936,38278753,38255808,38266387,38282068,38284455,38268958,38279626,38267739,38277105,38283918,38261913,38256137,38269453,38266340,38277058,38278245,38262900,38263786,38285419,38283824,38262775,38264174,38263779,38270446,38281510,38274653,38259146,38275841,38279412,38263118,38275614,38260248,38259100,38283144,38270473,38280594,38272557,38282097,38256810,38272377,38262320,38277138,38268737,38283812,38274872,38262124,38283762,38274241,38255230,38275060,38254353,38256645,38265714,38270084,38271795,38275556,38270936,38272132,38262196,38263435,38263294,38270552,38257024,38278083,38256620,38277855,38262211,38279307,38257813,38268271,38263869,38271758,38266912,38282575,38280100,38255455,38278657,38272226,38263934,38268706,38256577,38269144,38267084,38254153,38268890,38273295,38265183,38284602,38272129,38254835,38262315,38253384,38260069,38279759,38273948,38276209,38266011,38275216,38280382,38269460,38257284,38265857,38264196,38266792,38279009,38254656,38263621,38275542,38269959,38266216,38281451,38263175,38262949,38273624,38254386,38283929,38258101,38267879,38269656,38278338,38255004,38275645,38256392,38280883,38253425,38281864,38271775,38255884,38264876,38269754,38261524,38265088,38255609,38275816,38255496,38273097,38275555,38269737,38271450,38256558,38264269,38255157,38274210,38270649,38262120,38283833,38281895,38264906,38254405,38283399,38254265,38269744,38277220,38268256,38266867,38258880,38269265,38279912,38261415,38261232,38258129,38281316,38266907,38257118,38269650,38265390,38263909,38268803,38260658,38267526,38267740,38256516,38254291,38253315,38270529,38270360,38282969,38270325,38272993,38259204,38272738,38261797,38253406,38278966,38266131,38264937,38266569,38260815,38264742,38277798,38267787,38263051,38271705,38260217,38266144,38263386,38265665,38271216,38265092,38271030,38276810,38270904,38255001,38254866,38264118,38266833,38275554,38268462,38269297,38278350,38254435,38268376,38270044,38255923,38258381,38256166,38258778,38255892,38255404,38272804,38253414,38254786,38260555,38267588,38276741,38272165,38275557,38258015,38255168,38255056,38269475,38258671,38253699,38257182,38257860,38257816,38257310,38274427,38265884,38253434,38256694,38256509,38257049,38256206,38277201,38278074,38254719,38258958,38258718,38262536,38274212,38283562,38267616,38267020,38259727,38257045,38270323,38256707,38261187,38255834,38255789,38269897,38269747,38254589,38266934,38254080,38253937,38253749,38270817,38253521,38253364,38262182,38277774,38275874,38253825,38281944,38261914,38264580,38254040,38261666,38276883,38256951,38257457,38253582,38264687,38268470,38258078,38254138,38255079,38254258,38273223,38286163,38257637,38277430,38255692,38254502,38272845,38285730,38268043,38264778,38254086,38278786,38257141,38259436,38257000,38263833,38253715,38254096,38269445,38260982,38266042,38270582,38259638,38256746,38273528,38269175,38280479,38258842,38253787,38259220,38258406,38279375
        });
    }

    @Test
    @DisplayName("news")
    public void news() {
        HackerNews hackerNews = new HackerNews(HttpClient.newHttpClient());
        Assertions.assertEquals(hackerNews.news(38282166), "Blender 16yo winner of UK young animator of the year");
    }
}
