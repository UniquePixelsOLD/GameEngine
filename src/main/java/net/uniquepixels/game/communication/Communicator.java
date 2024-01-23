package net.uniquepixels.game.communication;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Communicator {

  private final JedisPool jedisPool;

  public Communicator() {
    this.jedisPool = new JedisPool("localhost", 6379);
  }

  public Jedis getPool() {
    return this.jedisPool.getResource();
  }

}
