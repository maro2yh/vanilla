package vanilla.stocks.html.naver;

public interface URLs {

    public static String FINANCE_HOME = "https://finance.naver.com";
    public static String MARKET_SISE = FINANCE_HOME + "/sise/sise_index.naver?code=%s";
    public static String MARKET_TIME = FINANCE_HOME + "/sise/sise_index_time.naver?code=%s&thistime=%s&page=%s";
    public static String MARKET_DAILY = FINANCE_HOME + "/sise/sise_index_day.naver?code=%s&page=%s";
    public static String SEARCH = FINANCE_HOME + "/search/searchList.nhn?query=%s";
    public static String UPJONG_LIST = FINANCE_HOME + "/sise/sise_group.nhn?type=upjong";
    public static String UPJONG_ITEMS = FINANCE_HOME + "/sise/sise_group_detail.nhn?type=upjong&no=%s";
    public static String THEME_LIST = FINANCE_HOME + "/sise/theme.nhn?page=%s";
    public static String THEME_ITEMS = FINANCE_HOME + "/sise/sise_group_detail.nhn?type=theme&no=%s";
    public static String INVESTOR_TREND_DAILY = FINANCE_HOME + "/sise/investorDealTrendDay.naver?bizdate=%s&sosok=%s&page=%s";
    public static String INVESTOR_TOP = FINANCE_HOME + "/sise/sise_deal_rank_iframe.naver?sosok=%s&investor_gubun=%s&type=%s";
    public static String ITEM_TOTAL_INFO = FINANCE_HOME + "/item/main.naver?code=%s";
    public static String ITEM_DAILY_SISE = FINANCE_HOME + "/item/sise_day.naver?code=%s&page=%s";
    public static String ITEM_INVESTOR_DAILY_TREND = FINANCE_HOME + "/item/frgn.naver?code=%s&page=%s";
}
