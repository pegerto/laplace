package com.github.pegerto.laplace.sampler

import akka.Done
import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.stream.SystemMaterializer
import akka.stream.scaladsl.Sink
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.ByteArrayDeserializer

import scala.concurrent.duration._

object KafkaSampler {

  private val actorSystem = ActorSystem("sampler")
  private implicit val materializer = SystemMaterializer(actorSystem).materializer

  private val kafkaConsumerSettings = ConsumerSettings(actorSystem, new ByteArrayDeserializer, new ByteArrayDeserializer)
    .withBootstrapServers("localhost:9092")
    .withGroupId("group")
    .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
    .withStopTimeout(0.seconds)

  private val sampler = Consumer
    .sourceWithOffsetContext(kafkaConsumerSettings, Subscriptions.topics("topic"))
    .map { consumerRecord =>
      println(consumerRecord)
    }
    .toMat(Sink.ignore)(Consumer.DrainingControl.apply)

  def run(): Consumer.DrainingControl[Done] = sampler.run()

}
