package arrays.intervals;

import java.util.Arrays;
import java.util.Comparator;

// Video Stitching
// You are given a series of video clips from a sporting event that lasted time seconds.
// These video clips can be overlapping with each other and have varying lengths.
//
// Each video clip is described by an array clips
// clips[i] = [starti, endi] indicates that the ith clip started at starti and ended at endi.
//
// We can cut these clips into segments freely.
// a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7]
//
// Return the minimum number of clips needed so that we can cut the clips into segments that
// cover the entire sporting event [0, time].
// If the task is impossible, return -1.
//
// 1 <= clips.length <= 100
// 0 <= starti <= endi <= 100
// 1 <= time <= 100
public class VideoStitching {

    // TODO. Greedy 在循环过程中不断选择最大的Intervals区间
    // clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
    // [0,2] + [1,9] + [8,10] = [0, 10] -> 3 clips
    //
    // 0-2  ok
    // 0-1
    //  1-5
    //  1----9  ok
    //   4-6
    //    5--9
    //      8-10 ok
    //
    // clips = [[0,1],[1,2]], time = 5 -> -1
    //
    // [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], time = 9
    // [0,4] + [4,7] + [6,9] = [0, 9] -> 3 clips
    //
    // [[5,7],[1,8],[0,0],[2,3],[4,5],[0,6],[5,10],[7,10]], times = 5
    // [0,6] -> 1 clip
    //
    // O(N*logN)
    // O(1)
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, Comparator.comparingInt(clip -> clip[0]));
        if (clips[0][0] > 0) {
            return -1;
        }

        int result = 1;

        int start = clips[0][0];
        int end = clips[0][1];
        int index = 1;
        while (index < clips.length) {
            // 如果目前的End已经满足，则直接返回
            if (end >= time) {
                return result;
            }

            // 排除掉不考虑的特殊情况
            if (end < clips[index][0]) {
                return -1;
            }
            if (clips[index][1] <= end) {
                index++;
                continue;
            }

            // 针对相同的起始点，选择最大的区间，不增加Clip统计
            if (start == clips[index][0]) {
                while (index < clips.length && start == clips[index][0]) {
                    end = Math.max(end, clips[index][1]);
                    index++;
                }
            } else {
                // 在后续可以扩充的区间中，选择最大的区间进行扩充，并统计一次
                int nextEnd = end;
                while (index < clips.length && clips[index][0] <= end) {
                    if (nextEnd < clips[index][1]) {
                        start = clips[index][0];
                        nextEnd = clips[index][1];
                    }
                    index++;
                }
                end = nextEnd;
                result++;
            }
        }

        // Check we can reach the end
        return end >= time ? result : -1;
    }
}
