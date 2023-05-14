import java.util.*;

class Solution {
      public static List<int[]> lists = new ArrayList<>();
    public static int m;
    public static int[] numbers;
    public static int[] points = {10, 20, 30, 40};

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new int[][] { {40, 10000}, {25, 10000}}, new int[]{7000, 9000});
    }
    public int[] solution(int[][] users, int[] emoticons) {
        m = emoticons.length;
        numbers = new int[m];
        dfs(0);
        List<User> userList = new ArrayList<>();
        for (int[] x : lists) {
            int userPlusCnt = 0;
            int userTotal = 0;

            for (int i=0; i<users.length; i++) {
                int minPer = users[i][0];
                int maxPrice = users[i][1];
                int total = 0;
                for (int j = 0; j < emoticons.length; j++) {
                    if (x[j] >= minPer) {
                        total += emoticons[j] * (100 - x[j]) / 100;
                    }
                }
                if (total >= maxPrice) {
                    userPlusCnt++;
                }else {
                    userTotal += total;
                }
            }
            userList.add(new User(userPlusCnt, userTotal));
        }

        userList.sort((o1, o2) -> {
           if (o1.plusCount == o2.plusCount) {
               return (o1.price - o2.price) * -1;
           }
           return (o1.plusCount - o2.plusCount) * -1;
        });

        return new int[]{userList.get(0).plusCount, userList.get(0).price};
    }
    public void dfs(int idx) {
        if (idx == m) {
            lists.add(Arrays.copyOf(numbers, m));
            return;
        }
        for (int i = 0; i < 4; i++) {
            numbers[idx] = points[i];
            dfs(idx + 1);
        }
    }

    static class User {
        private int plusCount;
        private int price;

        public User(int plusCount, int price) {
            this.plusCount = plusCount;
            this.price = price;
        }
    }
    
}