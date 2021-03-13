package com.epam.marketplace.internal;

import java.util.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InternalProcessesCommander implements ApplicationListener<ContextRefreshedEvent> {

  private final DealCloserTask dealCloserTask;

  @Autowired
  public InternalProcessesCommander(DealCloserTask dealCloserTask) {
    this.dealCloserTask = dealCloserTask;
  }
  private final Timer dealCloserTimer = new Timer(true);

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    long minute = 1000*60;
    dealCloserTimer.schedule(dealCloserTask, 0, minute);
  }
}
