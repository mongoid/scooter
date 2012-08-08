package org.scooter.io

import org.jboss.netty.channel.{ ChannelPipelineFactory, Channels }

/**
 * This is the pipeline that IO reads and writes will go through.
 */
class Pipeline extends ChannelPipelineFactory {

  /**
   * Get the ChannelPipeline for all IO.
   *
   * @return The ChannelPipeline.
   */
  def getPipeline = Channels.pipeline(new Encoder)
}
