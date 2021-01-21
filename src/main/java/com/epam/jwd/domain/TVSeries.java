package com.epam.jwd.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TVSeries extends Show {

    private final List<Season> seasons = new ArrayList<>();

    public TVSeries(int id, String title) {
        super(id, title);
    }

    class Season {
        private final int ind;
        private final List<Episode> episodes = new ArrayList<>();

        class Episode {
            private final int ind;
            private final String name;

            Episode(int num, String name) {
                this.ind = num;
                this.name = name;
                addEpisode(this);
            }

            public int getNum() {
                return ind+1;
            }

            public String getName() {
                return name;
            }
        }
        Season(int ind) {
            this.ind = ind;
            addSeason(this);
        }

        public void addEpisode(Episode episode) {
            episodes.add(episode);
        }

        public void addEpisodes(List<Episode> episodes) {
            this.episodes.addAll(episodes);
        }

        public int getNum() {
            return ind+1;
        }

        public List<Episode> getEpisodes() {
            return episodes;
        }

        public Episode getEpisodeByNum(int num) {
            return episodes.get(num - 1);
        }

    }

    public Season getSeasonByNum(int num) {
        return seasons.get(num);
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void addSeason(Season season) {
        seasons.add(season);
    }


}
