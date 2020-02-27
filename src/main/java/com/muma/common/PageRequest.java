//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.muma.common;

import com.muma.common.Sort.Direction;
import java.io.Serializable;

public class PageRequest implements Serializable {
    private static final long serialVersionUID = 8280485938848398236L;
    private int page;
    private int size;
    private Sort sort;
    private boolean countTotal;

    public PageRequest() {
        this.countTotal = true;
    }

    public PageRequest(int page, int size) {
        this(page, size, (Sort)null);
    }

    public PageRequest(int page, int size, Direction direction, String... properties) {
        this(page, size, new Sort(direction, properties));
    }

    public PageRequest(int page, int size, Sort sort) {
        this.countTotal = true;
        if (1 > page) {
            throw new IllegalArgumentException("Page index must not be less than one!");
        } else if (0 >= size) {
            throw new IllegalArgumentException("Page size must not be less than or equal to zero!");
        } else {
            this.page = page;
            this.size = size;
            this.sort = sort;
        }
    }

    public int getPageSize() {
        return this.size;
    }

    public int getPageNo() {
        return this.page;
    }

    public int getOffset() {
        return this.page * this.size;
    }

    public Sort getSort() {
        return this.sort;
    }

    public boolean isCountTotal() {
        return this.countTotal;
    }

    public void setCountTotal(boolean countTotal) {
        this.countTotal = countTotal;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof PageRequest)) {
            return false;
        } else {
            PageRequest that = (PageRequest)obj;
            boolean pageEqual = this.page == that.page;
            boolean sizeEqual = this.size == that.size;
            boolean sortEqual = this.sort == null ? that.sort == null : this.sort.equals(that.sort);
            return pageEqual && sizeEqual && sortEqual;
        }
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + this.page;
        result = 31 * result + this.size;
        result = 31 * result + (null == this.sort ? 0 : this.sort.hashCode());
        return result;
    }

    public static void main(String[] args) {
        new PageRequest(10, 10);
    }
}
