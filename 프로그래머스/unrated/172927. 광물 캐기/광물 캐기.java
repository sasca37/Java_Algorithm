import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        int maxLen = 0;
        for (int i=0; i< picks.length; i++) {
            maxLen += picks[i] * 5;
        }
        
        String[] copyMinerals = new String[maxLen];
        for (int i=0; i< maxLen; i++) {
            if (i < minerals.length) {
                copyMinerals[i] = minerals[i];    
            }
        }
        int cnt = copyMinerals.length / 5;
        
        // 0 : diamond, 1: iron , 2 : stone
        int[][] pirodo = new int[cnt][3];
                
        for (int i=0; i< cnt; i++) {
            pirodo[i][0] = getPirodo(0, i, copyMinerals);
            pirodo[i][1] = getPirodo(1, i, copyMinerals);
            pirodo[i][2] = getPirodo(2, i, copyMinerals);
        }
        Arrays.sort(pirodo, (o1, o2) -> (o2[2] - o1[2]));
        System.out.println(Arrays.deepToString(pirodo));
        
        for (int i=0; i< cnt; i++) {
            if (picks[0] != 0) {
                picks[0]--;
                answer += pirodo[i][0];
            }
            else if (picks[1] != 0) {
                picks[1]--;
                answer += pirodo[i][1];
            } else if (picks[2] != 0) {
                picks[2]--;
                answer += pirodo[i][2];
            }
        }
        
        return answer;
    }
    
    private int getPirodo(int gokNum, int cnt, String[] copyMinerals) {
        int pirodo = 0;
        for (int i=cnt*5; i < cnt*5 + 5; i++) {
            if (copyMinerals[i] == null) {
                break;
            }
            if (gokNum == 0) {
                pirodo += 1;
            } else if (gokNum == 1) {
                if (copyMinerals[i].equals("diamond")) {
                    pirodo += 5;
                }
                else {
                    pirodo += 1;
                }
            } else {
                if (copyMinerals[i].equals("diamond")) {
                    pirodo += 25;
                }
                else if (copyMinerals[i].equals("iron")) {
                    pirodo += 5;
                }
                else {
                    pirodo += 1;
                }
            }
        }
        return pirodo;
    }
}