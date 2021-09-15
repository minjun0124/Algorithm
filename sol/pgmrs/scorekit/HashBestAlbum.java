package Algorithm.sol.pgmrs.scorekit;

import java.util.*;

public class HashBestAlbum {
    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        solution(genres, plays);
    }

    private static int[] solution(String[] genres, int[] plays) {
        Queue<Integer> ansQueue = new LinkedList<>();
        Map<String, Integer> genresCnt = new HashMap<>();
        Map<String, PriorityQueue<Song>> songCnt = new HashMap<>();
        int idx = 0;
        for (String genre : genres) {
            genresCnt.put(genre, genresCnt.getOrDefault(genre, 0) + plays[idx]);
            PriorityQueue<Song> songQueue = songCnt.getOrDefault(genre, new PriorityQueue<Song>());
            songQueue.offer(new Song(idx, plays[idx]));
            songCnt.put(genre, songQueue);
            idx++;
        }
        PriorityQueue<Genre> pq = new PriorityQueue<Genre>();
        for (String key : genresCnt.keySet()) {
            pq.offer(new Genre(key, genresCnt.get(key)));
        }
        int cnt = 0;
        while (!pq.isEmpty()) {
            Genre genre = pq.poll();
            PriorityQueue<Song> songs = songCnt.get(genre.name);
            cnt = 0;
            while (!songs.isEmpty() && cnt < 2) {
                Song song = songs.poll();
                ansQueue.offer(song.idx);
                cnt++;
            }
        }

        int size = ansQueue.size();
        int[] answer = new int[size];
        for (int i = 0; i < size; i++) {
            answer[i] = ansQueue.poll();
        }

        return answer;
    }

    private static class Genre implements Comparable<Genre> {
        String name;
        int plays;

        public Genre(String name, int plays) {
            this.name = name;
            this.plays = plays;
        }

        @Override
        public int compareTo(Genre g) {
            return g.plays - this.plays;
        }
    }

    private static class Song implements Comparable<Song> {
        int idx;
        int plays;

        public Song(int idx, int plays) {
            this.idx = idx;
            this.plays = plays;
        }

        @Override
        public int compareTo(Song s) {
            return s.plays - this.plays;
        }
    }
}
