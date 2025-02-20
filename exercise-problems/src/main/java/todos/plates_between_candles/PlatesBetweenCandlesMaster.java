package todos.plates_between_candles;

// TODO. 目标算法: Binary search | Prefix Sum
public class PlatesBetweenCandlesMaster {

    // "||**||**|*"
    //     *||**|    query[3,8] = 2
    //
    // "**|**|***|"
    //    |**|       query[2,5] = 2
    //       |***|   query[5,9] = 3
    //
    // **||||*||||||*|||||||||*||**|*|*||*||**|*|||
    //
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int l = s.length();
        int[] pre = new int[l];
        int[] le = new int[l];
        int[] ri = new int[l];

        pre[0] = s.charAt(0)=='*' ? 1 : 0;
        for(int i=1; i<l; i++){
            if(s.charAt(i)=='*') pre[i]++;
            pre[i]+=pre[i-1];
        }
        for(int i=0; i<l; i++){
            if(s.charAt(i)=='|') le[i]=i;
            else{
                if(i==0) le[i]=-1;
                else le[i]=le[i-1];
            }
        }
        for(int i=l-1; i>=0; i--){
            if(s.charAt(i)=='|') ri[i]=i;
            else{
                if(i==l-1) ri[i]=-1;
                else ri[i]=ri[i+1];
            }
        }

        int[] ans = new int [queries.length];
        for(int i=0; i<queries.length; i++){
            int left=queries[i][0],right=queries[i][1];
            int lind=ri[left],rind=le[right];
            if(lind==-1 || rind==-1 || lind==rind || lind>right || rind<left) ans[i]=0;
            else ans[i]=pre[rind]-pre[lind];
        }
        return ans;
    }
}
