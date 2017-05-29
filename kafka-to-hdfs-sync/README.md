### Description
The Kafka to HDFS Application Template continuously reads messages from configured Apache Kafka topic(s) and writes each message as a line in Hadoop HDFS file(s).
- The size of the files and rotation policy can be configured. So no small file problem in HDFS.
- The application scales linearly with the number of Kafka brokers and Kafka topics.
- The application is fault tolerant and can withstand node and cluster outages without data loss.
- The application is also highly performant and can process faster than Kafka broker can produce per topic.
- It is extremely easy to add custom logic to get your business value without worrying about connectivity and operational details of Kafka consumer and HDFS writer.
- The only configuration user needs to provide is source Kafka broker list, topic list and destination HDFS path, filename.
- This enterprise grade application template will dramatically reduce your time to market and cost of operations.

Import the application from DataTorrent AppHub and launch it to read your data from Kafka topics and write to HDFS. Follow the tutorial videos or walkthrough document below to launch the template and add custom logic to process the data during ingestion.

### Tested with external sources
- Input Source:- Kafka (version: 0.9)

### Quickstart
Import and run application template as an operable proof of concept. Please watch the [walkthrough video](https://www.youtube.com/watch?v=d0RSeazfjN8) to import and launch the application.

<iframe src="https://www.youtube.com/embed/d0RSeazfjN8?enablejsapi=1" allowfullscreen="allowfullscreen" class="video" id="basicVideo" ga-track="basicVideo"></iframe>

### Productize
Add custom logic to the application template and launch. Please watch the [walkthrough video](https://www.youtube.com/watch?v=ftMiPECgyDQ) to add custom logic to the application template.

<iframe src="https://www.youtube.com/embed/ftMiPECgyDQ?enablejsapi=1" allowfullscreen="allowfullscreen" class="video" id="advancedVideo" ga-track="advancedVideo"></iframe>

### Logical Plan

Here is a preview of the logical plan of the application template

![Logical Plan](https://www.datatorrent.com/wp-content/uploads/2016/11/Kafka_to_HDFS_DAG.png)

### Launch App Properties

Here is a preview of the properties to be set at application launch

![Launch App Properties](https://www.datatorrent.com/wp-content/uploads/2016/11/Kafka_to_HDFS_properties.png)

### Resources

Please find the walkthrough docs for app template as follows:

&nbsp; <a href="http://docs.datatorrent.com/app-templates/kafka-to-hdfs-sync"  class="docs" id="docs" ga-track="docs" target="_blank">http://docs.datatorrent.com/app-templates/kafka-to-hdfs-sync</a>

Please find the GitHub URL for app template as follows:

&nbsp; <a href="https://github.com/DataTorrent/app-templates/tree/master/kafka-to-hdfs-sync"  class="github" id="github" ga-track="github" target="_blank">https://github.com/DataTorrent/app-templates/tree/master/kafka-to-hdfs-sync</a>

Please send feedback or feature requests to:

&nbsp; <a href="mailto:feedback@datatorrent.com"  class="feedback" id="feedback" ga-track="feedback">feedback@datatorrent.com</a>

Please join the user discussion groups:

&nbsp; <a href="mailto:dt-users@googlegroups.com"  class="maillist" id="maillist" ga-track="maillist">dt-users@googlegroups.com</a>
