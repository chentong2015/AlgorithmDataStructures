package googleTop50;

// RLE Iterator
// Implement the RLEIterator class:
//
// RLEIterator(int[] encoded) Initializes the object with the encoded array encoded.
// int next(int n) Exhausts the next n elements and returns the last element
// exhausted in this way. If there is no element left to exhaust, return -1 instead.
public class RLEIterator {

    int currentIndex;
    int leftCount;
    int[] encodedArray;

    public RLEIterator(int[] encoding) {
        this.encodedArray = encoding;
        this.currentIndex = 0;
        this.leftCount = encodedArray[currentIndex];
    }

    // TODO. 注意边界条件: 返回-1时要更新leftCount值, 避免返回遗留数据
    // O(N). N is num of int value
    // O(1)
    public int next(int n) {
        if (n > leftCount) {
            n -= leftCount;
            if (currentIndex == encodedArray.length - 2) { // Init初始化部分
                leftCount = 0;
                return -1;
            } else {
                currentIndex += 2;
                leftCount = encodedArray[currentIndex];
            }

            while (n > leftCount) {
                n -= leftCount;
                if (currentIndex == encodedArray.length - 2) { // 循环移动部分
                    leftCount = 0;
                    return -1;
                } else {
                    currentIndex += 2;
                    leftCount = encodedArray[currentIndex];
                }
            }
        }

        leftCount -= n;
        return encodedArray[currentIndex + 1];
    }
}