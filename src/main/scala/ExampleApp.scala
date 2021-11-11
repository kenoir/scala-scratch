import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.{Bucket, ListBucketsResponse}

import java.util
import scala.collection.{immutable, mutable}

import scala.collection.convert.ImplicitConversions._
import scala.jdk.CollectionConverters._

object ExampleApp extends App {
  val region = Region.US_WEST_2

  val profileName = "foo"

  val credentials = ProfileCredentialsProvider
    .create(profileName)

  val s3Client = S3Client.builder()
    .credentialsProvider(credentials)
    .region(region)
    .build();

  val bucketList: ListBucketsResponse = s3Client.listBuckets()
  val javaList: util.List[Bucket] = bucketList.buckets()

  // Deprecated uses scala.collection.convert.ImplicitConversions._
  javaList.toList

  val mutableSeq: mutable.Seq[Bucket] = javaList.asScala
  val scalaList: immutable.Seq[Bucket] =  javaList.asScala.toSeq
}
