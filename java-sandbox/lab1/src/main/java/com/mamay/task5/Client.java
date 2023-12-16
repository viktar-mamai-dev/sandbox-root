package com.mamay.task5;

import com.mamay.Lab1Exception;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Client extends Thread {
  private boolean reading = false;
  private final ChannelPool pool;

  public Client(ChannelPool pool) {
    this.pool = pool;
  }

  public void run() {
    AudioChannel channel = null;
    try {
      channel = pool.getResource(500);
      reading = true;
      log.debug("Channel Client #" + this.getId() + " took channel #" + channel.getChannelId());
      channel.using();
    } catch (Lab1Exception e) {
      log.debug("Client #" + this.getId() + " lost ->" + e.getMessage());
    } finally {
      if (channel != null) {
        reading = false;
        log.debug(
            "Channel Client #"
                + this.getId()
                + " : "
                + channel.getChannelId()
                + " channel released");
        pool.returnResource(channel);
      }
    }
  }

  public boolean isReading() {
    return reading;
  }
}
