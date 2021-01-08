package com.epam.jwd;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, SQLException {
//        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//        for (int i = 2; i < 2000; i++) {
//            HttpRequest httpRequest = HttpRequest.newBuilder(new URI("https://api.themoviedb.org/3/movie/"+i+"?api_key=96051ffb8f219b189cc2281bda95d3aa")).header("Accept", "application/json").GET().build();
//            HttpClient client = HttpClient.newHttpClient();
//            HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//            if(httpResponse.statusCode() != 200) continue;
//
//            MovieDTO movieDTO = objectMapper.readValue(httpResponse.body(), MovieDTO.class);
//            Movie movie = new Movie(
//                    -1,
//                    movieDTO.getTitle(),
//                    movieDTO.getReleaseDate(),
//                    "",
//                    movieDTO.getDescription(),
//                    "unknown",
//                    LocalTime.parse("00:00:00")
//            );
//            MovieDAO.getInstance().insert(movie);
//        }
//
//        // KinoRatingApplication.start();
    }
}
//@JsonIgnoreProperties(ignoreUnknown = true)
//class MovieDTO {
//    @JsonProperty("title")
//    private String title;
//    @JsonProperty("release_date")
//    private LocalDate releaseDate;
//    @JsonProperty("overview")
//    private String description;
//
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public LocalDate getReleaseDate() {
//        return releaseDate;
//    }
//
//    public void setReleaseDate(LocalDate releaseDate) {
//        this.releaseDate = releaseDate;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//}


