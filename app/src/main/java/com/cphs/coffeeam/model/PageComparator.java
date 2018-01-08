package com.cphs.coffeeam.model;

import java.util.Comparator;

/**
 * Created by cesario.siringoringo on 20/12/2017.
 */

public class PageComparator implements Comparator<Page> {
  @Override
  public int compare(Page e1, Page e2) {
    return e1.getChannel().getId().compareTo(e2.getChannel().getId());
  }
}
