### Description
The S3 to HDFS Sync Application Template continuously ingests files as blocks from the configured Amazon S3 location to the destination path in HDFS retaining one-to-one file traceability.
- The application scales linearly with number of block readers.
- The application is fault tolerant and can withstand node and cluster outages without data loss.
- The application is also highly performant and can perform as fast as the network bandwidth allows.
- The only configuration user needs to provide is source S3 connection parameters, bucket, directory and destination HDFS path, filename.
- This enterprise grade application template will dramatically reduce your time to market and cost of operations.

Import the application from DataTorrent AppHub and launch it to ingest your files from Amazon S3 to HDFS. Follow the tutorial videos or walkthrough document below to launch the template and run it.

### Quickstart
Import and run application template as an operable proof of concept. Please watch the [walkthrough video](https://www.youtube.com/watch?v=gA2eNL1wTCA) to import and launch the application.

<iframe src="https://www.youtube.com/embed/gA2eNL1wTCA?enablejsapi=1" allowfullscreen="allowfullscreen" class="video" id="basicVideo" ga-track="basicVideo"></iframe>

### Logical Plan

Here is a preview of the logical plan of the application template

![Logical Plan](http://datatorrent.com/wp-content/uploads/2016/11/S3_HDFS_Sync_App_DAG.png)

### Launch App Properties

Here is a preview of the properties to be set at application launch

![Launch App Properties](http://datatorrent.com/wp-content/uploads/2016/11/S3_HDFS_Sync_App_properties.png)

### Resources

Please find the walkthrough docs for app template as follows:

&nbsp; <a href="http://docs.datatorrent.com/app-templates/s3-to-hdfs-sync/"  class="docs" id="docs" ga-track="docs" target="_blank">http://docs.datatorrent.com/app-templates/s3-to-hdfs-sync/</a>

Please find the GitHub URL for app template as follows:

&nbsp; <a href="https://github.com/DataTorrent/app-templates/tree/master/s3-to-hdfs-sync"  class="github" id="github" ga-track="github" target="_blank">https://github.com/DataTorrent/app-templates/tree/master/s3-to-hdfs-sync</a>

Please send feedback or feature requests to:

&nbsp; <a href="mailto:feedback@datatorrent.com"  class="feedback" id="feedback" ga-track="feedback">feedback@datatorrent.com</a>

Please join the user discussion groups:

&nbsp; <a href="mailto:dt-users@googlegroups.com"  class="maillist" id="maillist" ga-track="maillist">dt-users@googlegroups.com</a>
