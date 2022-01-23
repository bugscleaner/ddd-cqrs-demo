package demo.dto;

/**
 * @author : kenny
 * @since : 2022/1/23
 **/
public class PageList<T>{
    private T mode;

    private int page;

    private int size;

    private int totalSize;

    public PageList(T mode, int page, int size, int totalSize) {
        this.mode = mode;
        this.page = page;
        this.size = size;
        this.totalSize = totalSize;
    }

    public T getMode() {
        return mode;
    }

    public void setMode(T mode) {
        this.mode = mode;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
