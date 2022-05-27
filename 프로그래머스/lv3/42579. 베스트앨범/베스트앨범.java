import java.util.*;

class Solution {
    public ArrayList<Integer> solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<String, Integer> genresHm = new HashMap<>();
        for (int i=0; i<genres.length; i++) {
            String word = genres[i];
            if (genresHm.containsKey(word)) {
                genresHm.put(word,genresHm.get(genres[i]) + plays[i]);
            } else {
                genresHm.put(word, plays[i]);
            }
        }
        ArrayList<Album> arr = new ArrayList<>();
        Object[] objects = genresHm.keySet().toArray();
        for (Object x : objects) {
            arr.add(new Album(genresHm.get((String)x), (String) x));
        }

        Collections.sort(arr, new Comparator<Album>() {
            @Override
            public int compare(Album o1, Album o2) {
                return (o1.play - o2.play) * -1;
            }
        });

        HashMap<String, ArrayList<Album>> hm = new HashMap<>();
        for (int i = 0; i < plays.length; i++) {
            if (!hm.containsKey(genres[i])) {
                ArrayList<Album> tmp = new ArrayList<>();
                tmp.add(new Album(i, plays[i]));
                hm.put(genres[i], tmp);
            }
            else {
                hm.get(genres[i]).add(new Album(i,plays[i]));
            }
        }

        for (Album x : arr) {
            ArrayList<Album> albums = hm.get(x.genres);
            Collections.sort(albums, new Comparator<Album>() {
                @Override
                public int compare(Album o1, Album o2) {
                    if (o1.play == o2.play) {
                        return o1.index - o2.index;
                    }
                    return (o1.play - o2.play) * -1;
                }
            });

            int maxG = 0;
            for (Album xx : albums) {
                if (++maxG > 2) break;
                answer.add(xx.index);
            }
        }

        return answer;
    }

    static class Album {
        int index, play;
        String genres;

        public Album(int index, int play) {
            this.index = index;
            this.play = play;
        }

        public Album(int play, String genres) {
            this.play = play;
            this.genres = genres;
        }
    }
}