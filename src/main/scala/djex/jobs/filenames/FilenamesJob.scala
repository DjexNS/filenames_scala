package djex.jobs.filenames

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.input_file_name

object FilenamesJob {

  lazy val spark: SparkSession = {
    SparkSession.builder().config("spark.speculation","false").master("local[*]").appName("FilenamesJob").getOrCreate()
  }

  def main(args: Array[String]) {

    val getOnlyFileName: UserDefinedFunction = spark.udf.register("getOnlyFileName", (fullPath: String) => fullPath.split("/").last)

    val initialDataframe = spark.read.text(s"s3a://path_to_the_common_root_folder/*/*/*")
      .withColumn("filename", getOnlyFileName(input_file_name))

    val collectedFilenamesList = initialDataframe.select("filename").distinct().rdd.map(r => r(0)).collect()
    collectedFilenamesList.foreach(println)

  }

}
