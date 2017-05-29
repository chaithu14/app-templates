### Description
The Kinesis to Redshift application ingest records separated by '|' from configured AWS kinesis streams and writes each message as a record in AWS redshift data warehouse. This application uses PoJoEvent as an example schema, this can be customized to use custom schema based on specific needs.

 The application scales linearly with number of Kinesis shards.
- The application is fault tolerant and can withstand node and cluster outages without data loss.
- The application is also highly performance and can perform as fast as the network bandwidth allows.
- The only configuration user needs to provide is source Kinesis credentials, stream name and destination redshift database table, bucket name, path and credentials.
- This enterprise grade application template will dramatically reduce your time to market and cost of operations.

Import the application from DataTorrent AppHub and launch it to ingest records separated by '|' from configured AWS Kinesis Stream and writes each message as a record in AWS Redshift DataBase. Follow the tutorial videos or walkthrough document below to launch the template and add custom logic to process the data during ingestion.

### Tested with external sources
- Input Source:- AWS Kinesis (version: 1.9.10)
- Output Source:- AWS Redshift JDBC driver (version: 1.2.1.1001)

### Quickstart
Import and run application template as an operable proof of concept. Please watch the [walkthrough video](https://ADD_LINK) to import and launch the application.

<iframe src="https://www.youtube.com/embed/Datatorrent" allowfullscreen="allowfullscreen" class="video" id="basicVideo" ga-track="basicVideo"></iframe>

### Productize
Add custom logic to the application template and launch. Please watch the [walkthrough video](https://ADD_LINK) to add custom logic to the application template.

<iframe src="https://www.youtube.com/embed/" allowfullscreen="allowfullscreen" class="video" id="advancedVideo" ga-track="advancedVideo"></iframe>

### Logical Plan

Here is a preview of the logical plan of the application template

![Logical Plan](https://www.datatorrent.com/wp-content/uploads/2016/12/kinesisToRedshift.png)

### Launch App Properties

Here is a preview of the properties to be set at application launch

![Launch App Properties](https://www.datatorrent.com/wp-content/uploads/2016/12/kinesisToRedshift.png)

### Resources

Please find the walkthrough docs for app template as follows:

&nbsp; <a href="http://docs.datatorrent.com/app-templates/kinesis-to-redshift"  class="docs" id="docs" ga-track="docs" target="_blank">http://docs.datatorrent.com/app-templates/kinesis-to-redshift</a>

Please find the GitHub URL for app template as follows:

&nbsp; <a href="https://github.com/DataTorrent/app-templates/tree/master/kinesis-to-redshift"  class="github" id="github" ga-track="github" target="_blank">https://github.com/DataTorrent/app-templates/tree/master/kinesis-to-redshift</a>

Please send feedback or feature requests to:

&nbsp; <a href="mailto:feedback@datatorrent.com"  class="feedback" id="feedback" ga-track="feedback">feedback@datatorrent.com</a>

Please join the user discussion groups:

&nbsp; <a href="mailto:dt-users@googlegroups.com"  class="maillist" id="maillist" ga-track="maillist">dt-users@googlegroups.com</a>
