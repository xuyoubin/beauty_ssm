//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.muma.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Page<T> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = 867755909294344406L;
    private List<T> content;
    private long total;
    private PageRequest pageRequest;

    public Page() {
        this.content = new ArrayList();
    }

    public Page(List<T> content, PageRequest pageRequest, long total) {
        this.content = new ArrayList();
        if (null == content) {
            throw new IllegalArgumentException("Content must not be null!");
        } else {
            this.content.addAll(content);
            this.total = total;
            this.pageRequest = pageRequest;
        }
    }

    public Page(List<T> content) {
        this(content, (PageRequest)null, null == content ? 0L : (long)content.size());
    }

    public int getPageNo() {
        return this.pageRequest == null ? 1 : this.pageRequest.getPageNo();
    }

    public int getPageSize() {
        return this.pageRequest == null ? 0 : this.pageRequest.getPageSize();
    }

    public int getTotalPages() {
        return this.getPageSize() == 0 ? 0 : (int)Math.ceil((double)this.total / (double)this.getPageSize());
    }

    public long getTotalElements() {
        return this.total;
    }

    public boolean hasPreviousPage() {
        return this.getPageNo() > 0;
    }

    public boolean isFirstPage() {
        return !this.hasPreviousPage();
    }

    public boolean hasNextPage() {
        return (long)((this.getPageNo() + 1) * this.getPageSize()) < this.total;
    }

    public boolean isLastPage() {
        return !this.hasNextPage();
    }

    public Iterator<T> iterator() {
        return this.content.iterator();
    }

    public List<T> getContent() {
        return Collections.unmodifiableList(this.content);
    }

    public boolean hasContent() {
        return !this.content.isEmpty();
    }

    public Sort getSort() {
        return this.pageRequest == null ? null : this.pageRequest.getSort();
    }

    public String toString() {
        String contentType = "UNKNOWN";
        if (this.content.size() > 0) {
            contentType = this.content.get(0).getClass().getName();
        }

        return String.format("Page %s of %d containing %s instances", this.getPageNo(), this.getTotalPages(), contentType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Page)) {
            return false;
        } else {
            Page<?> that = (Page)obj;
            boolean totalEqual = this.total == that.total;
            boolean contentEqual = this.content.equals(that.content);
            boolean pageRequestEqual = this.pageRequest == null ? that.pageRequest == null : this.pageRequest.equals(that.pageRequest);
            return totalEqual && contentEqual && pageRequestEqual;
        }
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + (int)(this.total ^ this.total >>> 32);
        result = 31 * result + (this.pageRequest == null ? 0 : this.pageRequest.hashCode());
        result = 31 * result + this.content.hashCode();
        return result;
    }
}
