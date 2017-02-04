package com.welovecoding.pagerefresher;

public class RefreshConfiguration {

  private String pageUrl = null;
  private String uninterestingText = null;
  private long waitInMillis = 1024;

  public RefreshConfiguration(String pageUrl, String uninterestingText) {
    this.pageUrl = pageUrl;
    this.uninterestingText = uninterestingText;
  }

  public String getPageUrl() {
    return pageUrl;
  }

  public void setPageUrl(String pageUrl) {
    this.pageUrl = pageUrl;
  }

  public String getUninterestingText() {
    return uninterestingText;
  }

  public void setUninterestingText(String uninterestingText) {
    this.uninterestingText = uninterestingText;
  }

  public long getWaitInMillis() {
    return waitInMillis;
  }

  public void setWaitInMillis(long waitInMillis) {
    this.waitInMillis = waitInMillis;
  }
}
