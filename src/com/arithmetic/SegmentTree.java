package com.arithmetic;

/***
 * 线段树
 * 取某范围内的数
 * 任意一个数，准备4N空间可形成满2叉数
 */
public class SegmentTree {
    private int maxN;
    private int[] arr;
    /*
    arr拆分成二叉树的累加和信息
     */
    private int[] sum;
    private int[] lazy;
    private int[] change;
    private boolean[] update;

    /***
     * 构造--初始化数组
     * @param origin
     */
    public SegmentTree(int[] origin){
        maxN = origin.length + 1;
        arr = new int[maxN];
        for(int i =1;i<maxN;i++){
            arr[i] = origin[i-1];
        }
        sum = new int[maxN << 2];
        lazy = new int[maxN << 2];
        change = new int[maxN << 2];
        update = new boolean[maxN << 2];
    }

    /***
     * 初始化 sum数据，将arr拆分成二叉树的累加和信息数据
     */
    public void build(int l,int r,int rt){
         if(l == r){
             sum[rt] = arr[l];
             return;
         }

         int mid = (r+l) >> 1;
         build(l,mid,rt << 1);
         build(mid+1, r ,rt << 1 | 1);
         pushUp(rt);
    }

    /***
     * 计算 1~t-1 层节点的数据
     * @param rt
     */
    private void pushUp(int rt){
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    /***
     * 懒加载
     * 在一个范围内增加一个数C，如：取数组arr下标 30000-120000 增加100
     * 时间复杂度 ：O(logN)
     * 传统做法，轮询一遍，时间复杂度log(N),如果数组趋近无穷大，此方法性能极差
     * @param L 取的范围左边值
     * @param R 取的范围右边值
     * @param C 任务增加的数
     * @param l 树节点左边值(arr的下标值)
     * @param r 树节点右边值(arr的下标值)
     * @param rt 树节点下标位置
     */
    public void add(int L,int R,int C,int l,int r,int rt){
        //任务在范围内
        if(l >= L && r <= R){
            sum[rt] += C * (r-l+1);
            lazy[rt] += C;
            return;
        }

        //任务没有被包含，下发任务
        int mid = (l+r) >> 1;
        //下发任务之前 lazy add任务
        pushDown(rt,mid -l+1, r-mid);
        if(L <= mid){
            add(L,R,C,l,mid,rt << 1);
        }
        if(R > mid){
            add(L,R,C,mid+1,r,rt << 1 | 1);
        }
        //下发完之后左右子节点数据更新后回更rt节点数据
        pushUp(rt);
    }

    /***
     * 懒更新
     * 在一定范围内更新值，下列参数如上add方法的参数,使用懒更新
     * @param L
     * @param R
     * @param C
     * @param l
     * @param r
     * @param rt
     */
    public void update(int L,int R,int C,int l,int r,int rt){
        //发生新增任务在范围内
        if(l >= L && r <= R){
            update[rt] = true;
            lazy[rt] = 0;
            sum[rt] = C * (r - l + 1) ;
            change[rt] = C;
            return;
        }
        //任务不在范围内，继续下发
        int mid = (l+r) >> 1;
        pushDown(rt,mid-l+1,r-mid);
        if(L <= mid){
            update(L,R,C,l,mid,rt << 1);
        }
        if(R > mid){
            update(L,R,C,mid+1,r,rt << 1 | 1);
        }
        pushUp(rt);
    }

    /***
     * 查询方法
     * 查询范围内 累加和
     * @param L
     * @param R
     * @param l
     * @param r
     * @param rt
     * @return
     */
    public long query(int L,int R,int l,int r,int rt){
        if(L <= l && R >= r){
            return sum[rt];
        }

        long res = 0;
        int mid = (l+r) >> 1;
        pushDown(rt,mid - l+1,r-mid);
        if(L <= mid){
            res += query(L,R,l,mid,rt << 1);
        }
        if(R > mid){
            res += query(L,R,mid+1,r,rt << 1 | 1);
        }

        return  res;
    }

    /***
     * 懒更新：从父范围，发给左右两个子范围
     * 懒增加：将节点元素被揽住的节点，其剩余节点全部累加 lazy[rt] ,其中rt 为sum 的下标
     * 其中更新内容最好在懒增加内容上面，如果lazy在上面，分发下一级时，需要先更新，在算sum数据，而如果update先执行，则可以直接更新，清空懒增加的数，因为先 add->update = update;update -> add != add
     * 分发策略：
     * @param rt 树节点下标位置
     * @param ln 左子树元素节点个数
     * @param rn 右子树节点个数  ----> 因为 lazy[rt] != 0 时才会下发更新子节点数，
     *           且 lazy[rt] != 0 的时候一定是在sum[rt]整个节点树都是被包含的，所以取ln 和 rn为左右两节点的元素个数，而不是任务范围分解后左右两边元素个数
     */
    public void pushDown(int rt,int ln,int rn){
        // 分发当前父节点任务到其子节点
        // 分发的内容: 更新状态、更新值；清空懒加载数（因为update[rt] 为true时，一定是其整个节点树都在更新范围内，所以其懒加载的值直接忽略）
        if(update[rt]){
            update[rt << 1] = true; //左子节点
            update[rt << 1 | 1] = true; //右子节点
            change[rt << 1] = change[rt]; //左子节点
            change[rt << 1 | 1] = change[rt]; //右子节点
            lazy[rt << 1] = 0;
            lazy[rt << 1 | 1] = 0;
            sum[rt << 1] = change[rt] * ln;
            sum[rt << 1] = change[rt] * rn;
            update[rt] = false;
        }

        // lazy 的意义：因为任务sum树节点在任务范围内会被揽住，不会再向下，所以当新任务下发，下发的位置超过子节点位置，
        // 上一次被拦住的位置继续向下的话需要增加lazy的信息，大概lazy的意义是没有新的任务下，无需将揽住的节点，其节点下所有的数更新
        // 只待新任务需要的时候更新其子节点信息，目的为，提升算法性能，节省计算时间,当有更新任务时lazy很好的可以抵消
        if(lazy[rt] != 0){
            lazy[rt << 1] += lazy[rt];  //左子树
            sum[rt << 1] += lazy[rt] * ln;  //左子树
            lazy[rt << 1 | 1] += lazy[rt];  //右子树
            sum[rt << 1 | 1] += lazy[rt] * rn;   //右子树
            lazy[rt] = 0;
        }
    }
}
