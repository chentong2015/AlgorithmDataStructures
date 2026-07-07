package arrays.intervals;

// Video Stitching
// You are given a series of video clips from a sporting event that lasted time seconds.
// These video clips can be overlapping with each other and have varying lengths.
//
// Each video clip is described by an array clips
// clips[i] = [starti, endi] indicates that the ith clip started at starti and ended at endi.
// We can cut these clips into segments freely.
// a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7]
//
// Return the minimum number of clips needed so that
// we can cut the clips into segments that cover the entire sporting event [0, time].
// If the task is impossible, return -1.
//
// 1 <= clips.length <= 100
// 0 <= starti <= endi <= 100
// 1 <= time <= 100
public class CombinedIntervalTarget {

    // TODO. Greedy贪心算法: 每次循环都从区间中取最大扩充的区间
    // clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
    // [0,2]+[1,9]+[8,10] = [0, 10] -> 3 clips
    //
    // O(N*M) N是clip数量, M是最小Clip数量
    // O(1)
    public int videoStitching(int[][] clips, int time) {
        int left = 0;
        int right = 0;
        int count = 0;

        while(right < time) {
            // 贪心差找下一个最大Cover区间(覆盖起始且有往后延伸)
            for(int[] clip : clips) {
                if(clip[0] <= left && clip[1] > right) {
                    right = clip[1];
                }
            }

            if(left == right) {  // 无法往后延伸!!
                return -1;
            }
            left = right; // 更新左侧的低值，并累计一个有效区间
            count++;
        }
        return count;
    }

    // 非贪心算法的解法:
    // 1. 对于起始点相同的区间，选择更长的
    // 2. 在当前区间延伸出去的下一个区间中，选择延伸更长的那个
}
