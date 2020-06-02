# Ingest Directory With Metadata Workflow
Original Author: IMT Global Inc.
Last Updated By: Ariel Laurella of IMT, 2020-06-02

####Objective

This workflow is intended to ingest in Reachengine all the contents of a file directory admitted by the system and to automatic catalogue the metadata contained in a sheet in CSV format in a single execution step.

##Appropriate use case  

The necessary elements that serve as input to submit this workflow are the following:
A directory with the files to be ingested.
An excel sheet in CSV format that contains the metadata associated with the files in the directory to be ingested.
A json file that maps the fields of the excel sheet header with the respective fields in reachengine.
A detail of the excel and json mapping fields is added at the end in the appendix.
This workflow must be operated as follows:
Submit in the UI the workflow called "Ingest Directory With Metadata". A form is prompted with the following required fields:
Directory to ingest.
Metadata file. 
Mapping file.

As a result of the process, the user expects that the contents of the selected directory will be ingested following the standard process required by Reachengine and that the respective metadata found in the excel sheet will be inputted.

The metadata completed in Reachengine corresponds to the following fields:
"recordOID","weekID","eventLocation","longDescription","shortDescription","keywords","preValidated","collection","category"
They correspond to the following fields in excel respectively:
"uuid","wid","location","description","short","keywords","validated","project","department"
The sheet has an additional column called "fileName". The conditions required for a file existing in the directory to be ingested are that it must be in the column "fileName" of the sheet and that there isn’t any asset in Reachengine with the same "recordOID".
The workflow must assign the required "category" / "department". It if fails, the asset automatically adds it to a category called "no department".
The workflow must assign the "collection"/"project" to the asset and create it if it doesn’t exist. If it fails, it adds it to the default collection called "orphaned".  

The workflow will set a metadata field called "proxyTriage" to the value "true" if any of the proxies or thumbnails fail to be created.

When the process is completed, the user expects that the files in the directory required will have been ingested and that they have the corresponding metadata fields filled. The user will receive an email with the result of all the ingests made with a success status or a fail status with the following fails:
An asset already exists in Reachengine with the same recordOID.
The addition of the collection failed and it was added to the default collection "orphaned".  
The assignment of category failed and it was added to the default category "no department".  

Workflow Limitations: 
This workflow ingests all the files found in the directory admitted by the system but no more than 11 simultaneously.
In order to ingest a file, it must have a reocordOID in the CSV excel sheet and there must not be another asset with the same recordOID in the system.

##Deployment instructions 

Before importing the workflows, the user must set up the following in Reachengine:

1. Indicate the local path in Reachengine where the CSV excel and JSON mapping files will be temporarily copied in order to process them. The system will delete them once they have been processed. This local path must be set up in the variable "localDirectoryPath" located in the main workflow "IngestDirectoryWithMetadata".
2. Create the following metadata fields from UI Admin-->Metadata-->Fields, according to the mapping provided in the mapping json. Make sure to put exactly Display Name and type, so you will see the name of the fields just like in the json mapping file of the specification. 

* Display Name: recordOID
  Type: Text(Samll)

* Display Name: weekID
  Type: Text(Samll)

* Display Name: Event Location
  Type: Picklist
  Configuration: Single
  Display Name: Long Description
  Type: Text(Large)

* Display Name: Short Description
  Type: Text(Samll)

* Display Name: keywords
  Type: Picklist
  Configuration: Multiple

* Display Name: Pre Validated
  Type: Checkbox

* Display Name: Proxy Triage
  Type: Checkbox

3. Verify that all the necessary categories have been inputted, including the category "no department".

4. Add the keywords to be entered in the keyword metadata field.

5. Import the workflows in the following order:
* a. ingestAssetBase
* b. documentProxiesCreate
* c. imageProxiesCreate
* d. audioProxiesCreate
* e. videoProxiesCreate
* f. proxiesAssetCreate
* g. saveAssetMetadata
* h. ingestAssetBase
* i. notificationsCreate
* j. IngestAssetWithMetadata
* k. IngestDirectoryWithMetadata

IMPORTANT: Once you define a metadata field, it is not possible to modify the Id, name and type. So, at this point, be careful to assign the proper field name and type of data.


## Workflow description

## Workflow: IngestDirectoryWithMetadata

This is the main workflow submitted by the UI. Here the user must input directory, CSV metadata file (.csv) and Metadata mapping file (.json). Once all of this data has been entered, the system starts the ingest following the steps indicated below:


## Workflows (subflow): IngestAssetWithMetadata
This is the workflow that manages each file sent from IngestDirectoryWithMetadata. It manages file ingest, proxy and thumbnail creation and the metadata saving. It sends the submission of each of these tasks to the workflows indicated below:

## Workflows (subflow): saveAssetMetadata and ingestAssetBase
This workflow is intended to save metadata in the asset ingested. They save all files required existing in the metadata file. 

## Workflows (subflow): proxiesAssetCreate
This workflow manages the creation of proxies and thumbnails for each file ingested. It invokes the specific subflow according to the asset type. 

## Workflows (subflows):  documentProxiesCreate, imageProxiesCreate, audioProxiesCreate, videoProxiesCreate.
All of these subflows are intended to create proxies and thumbnails of each file ingested. They are invoked from the subflow proxiesAssetCreate.
## Workflows (subflows):  notificationsCreate
This workflow is intended to create notification messages.


##Final note
This is a fair use of original workflows, property of Levels Beyond Inc. or IMT inc. 

