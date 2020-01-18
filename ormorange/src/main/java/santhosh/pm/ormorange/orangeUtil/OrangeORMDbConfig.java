package santhosh.pm.ormorange.orangeUtil;

import java.util.Locale;

/**
 * @author Santhosh Pagadala Mahesh
 */
public class OrangeORMDbConfig {

    /**
     * Tells SQLite which is the database default locale
     */
    private Locale databaseLocale;

    /**
     * Tells SQLite how much it can grow
     */
    private Long maxSize;

    /**
     * Tells SQLite the page size that have
     */
    private Long pageSize;

    public OrangeORMDbConfig () { }

    public Locale getDatabaseLocale() {
        return databaseLocale;
    }

    public OrangeORMDbConfig setDatabaseLocale( Locale databaseLocale) {
        this.databaseLocale = databaseLocale;
        return this;
    }

    public Long getMaxSize() {
        return maxSize;
    }

    public OrangeORMDbConfig setMaxSize( Long maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public OrangeORMDbConfig setPageSize( Long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public String toString() {
        return "OrangeORMDbConfig{" +
                ", databaseLocale=" + databaseLocale +
                ", maxSize=" + maxSize +
                ", pageSize=" + pageSize +
                '}';
    }
}
